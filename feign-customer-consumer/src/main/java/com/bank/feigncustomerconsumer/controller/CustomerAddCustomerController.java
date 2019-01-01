package com.bank.feigncustomerconsumer.controller;

import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.ICustomerAddCustomerService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/customer")
public class CustomerAddCustomerController {
    @Autowired
    private ICustomerAddCustomerService customerAddCustomerService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addCustomer(BankCustomer customer) {
        return customerAddCustomerService.addCustomer(customer);
    }
}
