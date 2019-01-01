package com.bank.feignconsumer1.controller;

import com.bank.feignconsumer1.service.ICustomerService;
import com.bank.feignconsumer1.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "customer/all", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllCustomer() {
        return customerService.getAllCustomer();
    }

}
