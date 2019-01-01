package com.bank.feigncustomerconsumer.controller;

import com.bank.feigncustomerconsumer.service.ICustomerGetOneService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/customer")
public class CustomerGetOneController {
    @Autowired
    private ICustomerGetOneService customerGetOneService;

    @RequestMapping(value = "/{custID}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getOneCustomer(@PathVariable String custID) {
        return customerGetOneService.getOneCustomer(custID);
    }
}
