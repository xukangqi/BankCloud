package com.bank.providercustomergetaccounts.controller;

import com.bank.providercustomergetaccounts.pojo.BankAccount;
import com.bank.providercustomergetaccounts.pojo.BankCustomer;
import com.bank.providercustomergetaccounts.service.IAccountService;
import com.bank.providercustomergetaccounts.service.ICustomerService;
import com.bank.providercustomergetaccounts.utils.BankResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    // 返回该id的客户所有账户信息（返回中文乱码）
    @RequestMapping(value = "/account/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAccounts(@PathVariable String custId) {
        List<BankAccount> accountList = accountService.getAccounts(custId);
        return new BankResult(200, "ok", accountList);
    }


}
