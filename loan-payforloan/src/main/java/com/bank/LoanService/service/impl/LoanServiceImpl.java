package com.bank.LoanService.service.impl;

import com.bank.LoanService.api.AccountServiceClient;
import com.bank.LoanService.api.CustomerServiceClient;
import com.bank.LoanService.api.LoanPaymentServiceClient;
import com.bank.LoanService.dao.*;
import com.bank.LoanService.pojo.*;
import com.bank.LoanService.pojo.loan.BankLoanPaymentInfo;
import com.bank.LoanService.service.LoanService;
import com.bank.LoanService.utils.BankResult;
import com.bank.LoanService.utils.DateControlForLoan;
import com.bank.LoanService.utils.MD5;
import com.bank.LoanService.utils.SnowFlake;
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
    private BankLoanPaylogMapper bankLoanPaylogMapper;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private LoanPaymentServiceClient loanPaymentServiceClient;


    private long datacenterId = 3L;  //数据中心
    private long machineId ;     //机器标识

    private double amountInAccount = 0.0;



    /**
     * 还款
     * @param bankLoanPaymentInfo
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "getErrorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            }
    )
    public BankResult payForLoan(BankLoanPaymentInfo bankLoanPaymentInfo) {
        machineId = 2L;
        // 查询信息是否异常
        BankResult isLegalInfo = isLegalInfo(bankLoanPaymentInfo.getAccount(),
                bankLoanPaymentInfo.getName(),bankLoanPaymentInfo.getIdCard(),bankLoanPaymentInfo.getPassword(),
                bankLoanPaymentInfo.getAmount(),true);
        if( isLegalInfo.getStatus() == 400 ) {
            return isLegalInfo;
        }

        //利用雪花算法算出 transId
        SnowFlake snowFlake = new SnowFlake(datacenterId,machineId);
        long payLogId = snowFlake.nextId();

        //交易时间戳
        String transTime = String.valueOf( System.currentTimeMillis() );

        BankLoanPaylog bankLoanPaylog = new BankLoanPaylog();
        bankLoanPaylog.setPaylogId(String.valueOf(payLogId));
        bankLoanPaylog.setPayDate(transTime);
        bankLoanPaylog.setAccount(bankLoanPaymentInfo.getAccount());
        bankLoanPaylog.setTransId(bankLoanPaymentInfo.getTransId() );
        bankLoanPaylog.setPayAmount( DateControlForLoan.toCriterionD(bankLoanPaymentInfo.getAmount()) );

        //插入
        try {
            bankLoanPaylogMapper.insert(bankLoanPaylog);
        } catch (Exception e) {
            return BankResult.build(400,"数据表插入异常");
        }

        BankResult result = loanPaymentServiceClient.payforLoan(bankLoanPaymentInfo.getTransId(),
                bankLoanPaymentInfo.getAmount(),bankLoanPaymentInfo.getAccount(),amountInAccount);
        return result;

    }
    //如果调用超时调用备用方法
    public BankResult getErrorMethod(BankLoanPaymentInfo bankLoanPaymentInfo) {
        return BankResult.build(400,"还款申请请求其他服务超时");
    }


    @Override
    public BankResult getPaylog(String value) {
        BankLoanPaylogExample bankLoanPaylogExample = new BankLoanPaylogExample();
        BankLoanPaylogExample.Criteria criteria = bankLoanPaylogExample.createCriteria();
        criteria.andTransIdEqualTo(value);
        try {
            List<BankLoanPaylog> bankLoanPaylogs = bankLoanPaylogMapper.selectByExample(bankLoanPaylogExample);
            return BankResult.ok(bankLoanPaylogs);
        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
    }
    /**
     * 判断账户合法性
     * @param account    账户号
     * @param custName   客户号
     * @param IdCard     IdCard
     * @param password   账户密码
     * @param amount     钱
     * @param flag       是否要进行卡内余额和金额的对比，true 表示要
     * @return           不合法，返回不合法原因；合法给出 custId，降低外界再次获得 custId 的开销
     */
    public BankResult isLegalInfo(String account, String custName,String IdCard,String password,
                                  String amount,boolean flag) {

        try {
            //判断 还钱数 是否是一个 double 数
            try {
                Double.valueOf(amount);
            }catch (NumberFormatException e) {
                return BankResult.build(400,"还款金额不合法");
            }
            // 判断账户是否存在
            ObjectMapper mapper = new ObjectMapper();
            BankResult res = accountServiceClient.getAccount(account);
            BankAccount bankAccount = mapper.convertValue(res.getData(),BankAccount.class);
            if(bankAccount == null) { return BankResult.build(400,"账户名不存在！"); }
            //判断密码是否匹配
            if(!MD5.string2MD5(password).equals(bankAccount.getPassword())) {
                return BankResult.build(400,"密码错误！");
            }
            // 判断姓名与账户是否匹配
            String custId = bankAccount.getCustId();
            BankCustomer bankCustomer = mapper.convertValue(customerServiceClient.getOneCustomer(custId).getData(),BankCustomer.class);
            if(bankCustomer == null) { return BankResult.build(400,"用户名不存在！"); }
            // 判断姓名账户
            if(!custName.equals(bankCustomer.getCustName()) ) {
                return BankResult.build(400,"姓名与账户不匹配！");
            }
            // 判断身份证号是否正确
            if(!IdCard.equals(bankCustomer.getIdentityCard())) {
                return BankResult.build(400,"身份证号不正确！");
            }
            //判断用户是否具有还款能里，银行卡里有没有还款的
            if(flag) {
                if( Double.valueOf(amount) > Double.valueOf(bankAccount.getBalances()) ) {
                    return BankResult.build(400,"还款金额超过账户余额");
                }
                amountInAccount = Double.valueOf(bankAccount.getBalances());
            }
            return BankResult.build(200,"OK",custId); //这个和你没关系

        } catch (Exception e) {
            System.out.println(e);
            return BankResult.build(400,"数据表查询异常");
        }

    }


}