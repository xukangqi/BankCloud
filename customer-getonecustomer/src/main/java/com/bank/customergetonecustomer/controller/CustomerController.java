package com.bank.customergetonecustomer.controller;

import com.bank.customergetonecustomer.pojo.BankCustomer;
import com.bank.customergetonecustomer.service.IAccountService;
import com.bank.customergetonecustomer.service.ICustomerService;
import com.bank.customergetonecustomer.utils.BankResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lujiafeng on 2018/10/17.
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;

    // 返回单个客户信息（已测试）
    @RequestMapping(value = "/{custID}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getOneCustomer(@PathVariable String custID) {
        BankCustomer customer = customerService.getCustomerById(custID);
        BankResult bankResult = new BankResult(200, "ok", customer);
        return bankResult;
    }

}
