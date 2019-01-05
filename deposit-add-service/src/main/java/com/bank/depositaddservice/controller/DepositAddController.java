package com.bank.depositaddservice.controller;

import com.bank.depositaddservice.pojo.BankDeposit;
import com.bank.depositaddservice.service.DepositAddService;
import com.bank.depositaddservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/deposit")
public class DepositAddController {

    @Autowired
    private DepositAddService depositAddService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BankResult add(@RequestParam("bankDeposit") BankDeposit bankDeposit, @RequestParam("password")String password){
        return depositAddService.insert(bankDeposit, password);
    }

}
