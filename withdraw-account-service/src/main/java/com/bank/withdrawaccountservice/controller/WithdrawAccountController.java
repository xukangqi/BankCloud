package com.bank.withdrawaccountservice.controller;

import com.bank.withdrawaccountservice.service.WithdrawAccountService;
import com.bank.withdrawaccountservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/withdraw")
public class WithdrawAccountController {

    @Autowired
    private WithdrawAccountService withdrawAccountService;

    @RequestMapping(value = "/{account}")
    public BankResult getBankDepositFromAccount(@PathVariable("account") String account){
        return withdrawAccountService.getBankWithdrawByAccount(account);
    }
}
