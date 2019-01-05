package com.bank.LoanService.service.impl;

import com.bank.LoanService.api.AccountServiceClient;
import com.bank.LoanService.api.LoanDataServiceClient;
import com.bank.LoanService.dao.*;
import com.bank.LoanService.pojo.*;
import com.bank.LoanService.service.LoanService;
import com.bank.LoanService.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZJ
 */
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private BankLoanPaymentMapper bankLoanPaymentMapper;

    @Autowired
    private LoanDataServiceClient loanDataServiceClient;

    @Autowired
    private AccountServiceClient accountServiceClient;

    private double amountInAccount = 0.0;

    //插入贷款还款数据
    public BankResult insertData(BankLoanPayment bankLoan){
        try
        {
            bankLoanPaymentMapper.insert(bankLoan);
            return BankResult.ok();
        }catch (Exception e){
            return BankResult.build(400,"数据库插入异常",null);
        }
    }


    @Override
    @HystrixCommand(fallbackMethod = "getErrorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    //传入transId，执行还款操作
    public BankResult payforLoan(String transId,String amounts,String account,Double amountInAcc){
        amountInAccount = amountInAcc;
        //判断 transId 是否是属于account的一个合法 贷款号，并进行计算
        try {
            ObjectMapper mapper = new ObjectMapper();
            BankResult res = loanDataServiceClient.getOneRecord(transId);
            if (res.getStatus()!=200) return res;
            BankLoan bankLoan = mapper.convertValue(res.getData(),BankLoan.class);
            String accountTemp = bankLoan.getAccount();
            if( !accountTemp.equals( account) ) {
                return BankResult.build(400,"账户号有误");
            }
            //查询 transId 的贷款记录
            BankLoanPaymentExample bankLoanPaymentExample = new BankLoanPaymentExample();
            bankLoanPaymentExample.setOrderByClause("ins_num ASC");
            BankLoanPaymentExample.Criteria criteria = bankLoanPaymentExample.createCriteria();
            criteria.andTransIdEqualTo(transId);
            criteria.andIsFinishedEqualTo("false");
            List<BankLoanPayment> bankLoanPaymentList = bankLoanPaymentMapper.selectByExample(bankLoanPaymentExample);
            //现在时间戳
            long nowTime = System.currentTimeMillis();  //时间戳
            double amount = Long.valueOf( amounts );    //还款额，可能会有剩余
            boolean overDueFlag = false;    //是否有逾期行为
            boolean overDueNotPay = false;  //有逾期账单
            boolean allPayed = true;
            double fineAll = 0.0;   //罚金总数，用于表 bank_loan 的更新
            double recoveredMoney ; //本次还了多少
            for (int i = 0; i < bankLoanPaymentList.size(); i++) {
                BankLoanPayment bankLoanPayment = bankLoanPaymentList.get(i);
                //超期  所有超期的都应该算罚金，无论 amount 是否为 0
                if(nowTime > Long.valueOf(bankLoanPaymentList.get(i).getPaymentDate()) ) {
                    overDueFlag = true;
                    //计算之前的罚金 罚息按照天算 neededMoney = 总共需要还的钱 - 已经还的钱
                    double neededMoney = bankLoanPayment.getAllPaymentAmount() - bankLoanPayment.getReimbursement();
                    //迭代
                    for (long j = Long.valueOf(bankLoanPayment.getPaymentDate()); j < nowTime ; j += 86400000L) {
                        neededMoney += neededMoney * bankLoanPayment.getFineRate() / 100;
                    }
                    //上次时间戳改变到今天的 23:59:59
                    double fineMoney = neededMoney - ( bankLoanPayment.getAllPaymentAmount()
                            - bankLoanPayment.getReimbursement() );
                    fineAll += fineMoney;   //罚金加钱
                    BankLoanPayment bankLoanPayment1 = new BankLoanPayment();
                    bankLoanPayment1.setPaymentId(bankLoanPayment.getPaymentId());
                    bankLoanPayment1.setPaymentDate(DateControlForLoan.
                            getTimestampAboutEndOfDay());   //获取该天的23:59:59的时间戳 ，防止罚金被重复算
                    bankLoanPayment1.setAllPaymentAmount(DateControlForLoan.toCriterionD
                            (bankLoanPayment.getAllPaymentAmount() + fineMoney));
                    // 钱够还这期
                    if(amount >= neededMoney) {
                        amount -= neededMoney;
                        bankLoanPayment1.setReimbursement(DateControlForLoan.toCriterionD
                                (bankLoanPayment.getReimbursement() + neededMoney) );    //默认这一期先还
                        bankLoanPayment1.setIsFinished("true");     //已还清
                    }
                    else {
                        //还没还清
                        overDueNotPay = true;
                        allPayed = false;
                        bankLoanPayment1.setReimbursement(DateControlForLoan.toCriterionD
                                (bankLoanPayment.getReimbursement() + amount));
                        amount = 0;
                    }
                    try {
                        bankLoanPaymentMapper.updateByPrimaryKeySelective(bankLoanPayment1);
                    }catch (Exception e) {
                        return BankResult.build(400,"数据表插入异常");
                    }
                }
                else {  //不需要算罚金
                    double neededMoney = bankLoanPayment.getAllPaymentAmount() - bankLoanPayment.getReimbursement();
                    BankLoanPayment bankLoanPayment2 = new BankLoanPayment();
                    bankLoanPayment2.setPaymentId(bankLoanPayment.getPaymentId());
                    // 钱够还这期
                    if(amount >= neededMoney) {
                        amount -= neededMoney;
                        bankLoanPayment2.setReimbursement(DateControlForLoan.toCriterionD
                                (bankLoanPayment.getReimbursement() + neededMoney ));
                        bankLoanPayment2.setIsFinished("true");     //已还清
                    }
                    else {
                        //还没还清
                        bankLoanPayment2.setReimbursement(DateControlForLoan.toCriterionD
                                (bankLoanPayment.getReimbursement() + amount));
                        amount = 0.0;
                        allPayed = false;
                    }
                    try {
                        bankLoanPaymentMapper.updateByPrimaryKeySelective(bankLoanPayment2);
                    }catch (Exception e) {
                        return BankResult.build(400,"数据表更新异常");
                    }
                }
            }

            // 向原账户扣钱
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccount(account);
            // 账户扣款金额 = 账户原有额 - 预还款金额 + 还款后还多的金额
            bankAccount.setBalances(DateControlForLoan.toCriterionD(amountInAccount- Double.valueOf(amounts) + amount));
            if(overDueFlag) {
                bankAccount.setAccountStatus("2");
            }
            try {
               BankResult res1 = accountServiceClient.updateAccountSel(bankAccount);
               if (res1.getStatus()!=200) return res1;
            } catch (Exception e) {
                return BankResult.build(400,"数据表更新异常");
            }


            //bankLoan 的修改
            recoveredMoney = Double.valueOf(amounts) - amount;
            BankLoan bankLoan1 = new BankLoan();
            bankLoan1.setTransId(bankLoan.getTransId());
            bankLoan1.setRecoveredAmount(DateControlForLoan.toCriterionD
                    (bankLoan.getRecoveredAmount() + recoveredMoney));
            bankLoan1.setLoanAmountSum(DateControlForLoan.toCriterionD
                    (bankLoan.getLoanAmountSum() + fineAll));
            //时间到了 并已经还清
            if(allPayed) {
                bankLoan1.setLoanStatus("已还清");
            }
            if(overDueNotPay) {
                bankLoan1.setLoanStatus("逾期");
            }
            try {
                BankResult result = loanDataServiceClient.UpdateLoan(bankLoan1);
                if (result.getStatus()!=200) return result;
            }catch (Exception e) {
                return BankResult.build(400,"数据表更新异常");
            }

        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
        return BankResult.build(200,"还款成功！");
    }

    //如果调用超时调用备用方法
    public BankResult getErrorMethod(String transId,String amounts,String account,Double amountInAcc) {
        return BankResult.build(400,"还款核心操作请求其他服务超时");
    }

    @Override
    public  BankResult getPaymentOneInfo(String value) {
        BankLoanPaymentExample bankLoanPaymentExample = new BankLoanPaymentExample();
        BankLoanPaymentExample.Criteria criteria = bankLoanPaymentExample.createCriteria();
        criteria.andTransIdEqualTo(value);
        try {
            List<BankLoanPayment> bankLoanPaymentList = bankLoanPaymentMapper.selectByExample(bankLoanPaymentExample);
            return BankResult.ok(bankLoanPaymentList);
        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
    }

}