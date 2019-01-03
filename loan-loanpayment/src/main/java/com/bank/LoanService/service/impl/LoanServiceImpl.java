package com.bank.LoanService.service.impl;

import com.bank.LoanService.dao.*;
import com.bank.LoanService.pojo.*;
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
    private BankLoanPaymentMapper bankLoanPaymentMapper;

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
    //更新贷款还款数据
    public BankResult updateByPrimaryKeySelective(BankLoanPayment bankLoan){
        try{
            bankLoanPaymentMapper.updateByPrimaryKeySelective(bankLoan);
            return BankResult.ok();
        }catch (Exception e){
            return BankResult.build(400,"数据库更新异常",null);
        }
    }

    //通过example查询数据库
    public BankResult selectByExample(BankLoanPaymentExample bankLoanPaymentExample){
        try{
            List<BankLoanPayment> bankLoanPaymentList= bankLoanPaymentMapper.selectByExample(bankLoanPaymentExample);
            return BankResult.ok(bankLoanPaymentList);
        }catch (Exception e){
            return BankResult.build(400,"数据库查询异常",null);
        }
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