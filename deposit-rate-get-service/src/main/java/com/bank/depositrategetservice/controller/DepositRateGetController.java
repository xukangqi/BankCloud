package com.bank.depositrategetservice.controller;

import com.bank.depositrategetservice.service.DepositRateGetService;
import com.bank.depositrategetservice.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositRateGetController {

    @Autowired
    private DepositRateGetService depositRateGetService;

    @RequestMapping(value = "/getRate", method = RequestMethod.GET)
    public BankResult getAllRate(){
        return depositRateGetService.getAllRate();
    }

}
