package com.bank.LoanService.service.impl;

import com.bank.LoanService.dao.*;
import com.bank.LoanService.pojo.*;
import com.bank.LoanService.pojo.loan.BankLoanApplyInfo;
import com.bank.LoanService.service.LoanService;
import com.bank.LoanService.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZJ
 */
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private BankLoanTypeMapper bankLoanTypeMapper;


    private long datacenterId = 3L;  //数据中心
    private long machineId ;     //机器标识

    private double amountInAccount = 0.0;

    /**
     * 贷款申请
     * @param bankLoanApplyInfo
     * @return
     */
    @Override
    public BankResult dealApplyment(BankLoanApplyInfo bankLoanApplyInfo) {

        machineId = 1L;

        //判断用法合理性
        String account = bankLoanApplyInfo.getAccount();
        BankResult isLegalInfo = isLegalInfo(account,bankLoanApplyInfo.getName(),bankLoanApplyInfo.getIdCard(),
                bankLoanApplyInfo.getPassword(),bankLoanApplyInfo.getAmount(),false);
        if( isLegalInfo.getStatus() == 400 ) {
            return isLegalInfo;
        }
        String custId = isLegalInfo.getData().toString();
        System.out.println(custId);
        isLegalInfo = null;

        //利用雪花算法算出 transId
        SnowFlake snowFlake = new SnowFlake(datacenterId,machineId);
        long transId = snowFlake.nextId();

        //还款总额度 以"月"为单位，利息是年息
        double tempSum = DateControlForLoan.toCriterionD( bankLoanApplyInfo.getAmount() ) *
                (1 + DateControlForLoan.toCriterionD(bankLoanApplyInfo.getInterestRate())*0.01*
                        Short.valueOf( bankLoanApplyInfo.getTime())/12  );

        //住房类型
        String loanType = null;
        switch (bankLoanApplyInfo.getLoanType()) {
            case ("1") : loanType = "住房贷款"; break;
            case ("2") : loanType = "小微贷款"; break;
            case ("3") : loanType = "消费贷款"; break;
//            case ("4") : loanType = "信用贷款"; break;
            default: return BankResult.build(400,"贷款类型不存在！");
        }

        //交易时间戳
        String transDate = String.valueOf( System.currentTimeMillis() );

        //插入数据
        BankLoan bankLoan = new BankLoan();
        bankLoan.setTransId( String.valueOf(transId) );
        bankLoan.setCustId( custId );
        bankLoan.setAccount( account );
        bankLoan.setTransDate( transDate );
        bankLoan.setLoanAmount( DateControlForLoan.toCriterionD( bankLoanApplyInfo.getAmount() ));
        bankLoan.setInsCount(Short.valueOf( bankLoanApplyInfo.getTime() ));
        bankLoan.setLoanInterest( DateControlForLoan.toCriterionD(bankLoanApplyInfo.getInterestRate() ));
        bankLoan.setLoanAmountSum( DateControlForLoan.toCriterionD(  tempSum  ) );
        bankLoan.setExpirationDate( DateControlForLoan.getExpirationDate(1 + Short.valueOf( bankLoanApplyInfo.getTime() )) );
        bankLoan.setRecoveredAmount( 0.00 );
        bankLoan.setLoanTypeName( loanType );
        bankLoan.setLoanStatus( "未到期" );
        //TODO 审核员ID 待插入
        try {
            bankLoanMapper.insert(bankLoan);
        } catch (Exception e) {
            return BankResult.build(400,"数据表插入异常");
        }

        //生成用户 payment 表，里面包括了用户在 n 期内每期的操作
        machineId = 2L;
        SnowFlake snowFlake1 = new SnowFlake(datacenterId,machineId);
        double fineRate = bankLoanTypeMapper.selectByPrimaryKey(loanType).getFineRate();
        double payMoney = DateControlForLoan.toCriterionD( tempSum /
                Short.valueOf(bankLoanApplyInfo.getTime()) );
        double payMoney1 = payMoney + DateControlForLoan.toCriterionD(tempSum) -
                payMoney * Short.valueOf(bankLoanApplyInfo.getTime());
        payMoney1 = DateControlForLoan.toCriterionD(payMoney1 );
        for (int i = 0; i < Short.valueOf( bankLoanApplyInfo.getTime() ); i++) {
            long paymentId = snowFlake.nextId();
            BankLoanPayment bankLoanPayment = new BankLoanPayment();
            //将 payment_date 设置为到期日
            bankLoanPayment.setPaymentId(String.valueOf(paymentId));
            bankLoanPayment.setTransId(String.valueOf(transId));
            bankLoanPayment.setInsNum( Short.valueOf(i+1+"") ) ;
            if(i == 0) {
                bankLoanPayment.setPaymentAmount( payMoney1 );
                bankLoanPayment.setAllPaymentAmount(payMoney1);
            } else {
                bankLoanPayment.setPaymentAmount( payMoney );
                bankLoanPayment.setAllPaymentAmount( payMoney );
            }
            bankLoanPayment.setPaymentDate( DateControlForLoan.getExpirationDate(i+2) );
            bankLoanPayment.setIsFinished("false");
            bankLoanPayment.setFineRate(fineRate);
            bankLoanPayment.setReimbursement(0.00);
            try {
                bankLoanPaymentMapper.insert(bankLoanPayment);
            } catch (Exception e) {
                return BankResult.build(400,"数据表插入异常");
            }
        }

        return BankResult.build(200,"交易成功！");
    }






    @Override
    public BankResult getInterestRate() {
        BankLoanTypeExample bankLoanTypeExample = new BankLoanTypeExample();
        try {
            List<BankLoanType> bankLoanTypeList = bankLoanTypeMapper.selectByExample(bankLoanTypeExample);
            return BankResult.ok(bankLoanTypeList);
        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
    }

    @Override
    public BankResult getInterestOneRate(int value) {
        String text = null;
        switch (value) {
            case 1: text = "住房贷款";break;
            case 2: text = "小微贷款";break;
            case 3: text = "消费贷款";break;
            default: return BankResult.build(400,"请求错误");
        }

        try {
            BankLoanType bankLoanType = bankLoanTypeMapper.selectByPrimaryKey(text);
            return BankResult.ok(bankLoanType);
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
            BankAccount bankAccount = bankAccountMapper.selectByPrimaryKey(account);
            if(bankAccount == null) { return BankResult.build(400,"账户名不存在！"); }
            //判断密码是否匹配
            if(!MD5.string2MD5(password).equals(bankAccount.getPassword())) {
                return BankResult.build(400,"密码错误！");
            }
            // 判断姓名与账户是否匹配
            String custId = bankAccount.getCustId();
            BankCustomer bankCustomer = bankCustomerMapper.selectByPrimaryKey(custId);
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
            return BankResult.build(400,"数据表查询异常");
        }

    }


}