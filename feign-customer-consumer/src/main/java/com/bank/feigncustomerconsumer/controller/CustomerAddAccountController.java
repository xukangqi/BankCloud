package com.bank.feigncustomerconsumer.controller;


import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.service.ICustomerAddAccountService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/customer")
public class CustomerAddAccountController {
    @Autowired
    private ICustomerAddAccountService customerAddAccountService;

    @RequestMapping(value = "/account/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addAccount(@RequestParam(value = "custName") String custName, BankAccount account) {
        return customerAddAccountService.addAccount(custName, account);
    }

}
