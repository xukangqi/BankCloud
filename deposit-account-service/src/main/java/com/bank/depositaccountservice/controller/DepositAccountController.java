package com.bank.depositaccountservice.controller;

import com.bank.depositaccountservice.service.DepositAccountService;
import com.bank.depositaccountservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/deposit")
public class DepositAccountController {

    @Autowired
    private DepositAccountService depositAccountService;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public BankResult getDepositByAccount(@RequestParam("account")String account){
        return depositAccountService.getBankDepositFromAccount(account);
    }
}
