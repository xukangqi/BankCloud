package com.bank.depositrateservice.controller;

import com.bank.depositrateservice.pojo.BankDepositRate;
import com.bank.depositrateservice.service.DepositRateService;
import com.bank.depositrateservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositRateController {

    @Autowired
    private DepositRateService depositRateService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BankResult insert(BankDepositRate bankDepositRate){
        return depositRateService.insert(bankDepositRate);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BankResult update(BankDepositRate bankDepositRate){
        return depositRateService.update(bankDepositRate);
    }
}
