package com.bank.withdrawaccountservice.service.impl;

import com.bank.withdrawaccountservice.mapper.BankWithdrawMapper;
import com.bank.withdrawaccountservice.pojo.BankWithdraw;
import com.bank.withdrawaccountservice.pojo.BankWithdrawExample;
import com.bank.withdrawaccountservice.service.WithdrawAccountService;
import com.bank.withdrawaccountservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawAccountServiceImpl implements WithdrawAccountService {

    @Autowired
    private BankWithdrawMapper bankWithdrawMapper;

    @Override
    public BankResult getBankWithdrawByAccount(String account) {
        if (account == null || account.equals("")) {
            return BankResult.build(400, "参数错误");
        }
        BankWithdrawExample bankWithdrawExample = new BankWithdrawExample();
        bankWithdrawExample.createCriteria().andAccountEqualTo(account);
        bankWithdrawExample.setOrderByClause("withdraw_id desc");
        List<BankWithdraw> bankWithdraws = bankWithdrawMapper.selectByExample(bankWithdrawExample);
        return new BankResult(200, "查询成功", bankWithdraws);
    }
}
