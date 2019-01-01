package com.bank.feigncustomerconsumer.controller;

import com.bank.feigncustomerconsumer.service.ICustomerGetAccountsService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/customer")
public class CustomerGetAccountsController {
    @Autowired
    private ICustomerGetAccountsService customerGetAccountsService;

    @RequestMapping(value = "/account/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAccounts(@PathVariable String custId) {
        return customerGetAccountsService.getAccounts(custId);
    }
}