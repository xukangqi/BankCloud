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
    private BankLoanMapper bankLoanMapper;

    //插入贷款数据
    public BankResult insertData(BankLoan bankLoan){
        try
        {
            bankLoanMapper.insert(bankLoan);
            return BankResult.ok();
        }catch (Exception e){
            return BankResult.build(400,"数据库插入异常",null);
        }
    }
    //更新贷款数据
    public BankResult updateByPrimaryKeySelective(BankLoan bankLoan){
        try{
            bankLoanMapper.updateByPrimaryKeySelective(bankLoan);
            return BankResult.ok();
        }catch (Exception e){
            return BankResult.build(400,"数据库更新异常",null);
        }
    }

    /**
     * 获取所有的贷款信息
     * @return
     */
    @Override
    public BankResult sentAllRecords() {
        BankLoanExample bankLoanExample = new BankLoanExample();
        try {
            List<BankLoan> bankLoanList = bankLoanMapper.selectByExample(bankLoanExample);
            System.out.println(bankLoanList.get(0).getTransId());
            return BankResult.ok(bankLoanList);
        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
    }

    /**
     * 获取单个贷款信息
     * @param transId 交易号
     * @return
     */
    @Override
    public BankResult sentOneRecord(String transId) {
        try {
            BankLoan bankLoan = bankLoanMapper.selectByPrimaryKey(transId);
            if(bankLoan == null)
                return BankResult.build(400,"贷款交易号不存在，请检查输入正确性");
            return BankResult.ok(bankLoan);
        } catch (Exception e) {
            return BankResult.build(400,"数据表查询异常");
        }
    }

}