package com.bank.providercustomeraddaccount.controller;

import com.bank.providercustomeraddaccount.pojo.BankAccount;
import com.bank.providercustomeraddaccount.pojo.BankCustomer;
import com.bank.providercustomeraddaccount.service.IAccountService;
import com.bank.providercustomeraddaccount.service.ICustomerService;
import com.bank.providercustomeraddaccount.utils.BankResult;
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


    // 新建一个账户（已测试）
    @RequestMapping(value = "/account/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addAccount(@RequestParam(value = "custName") String custName, @RequestBody BankAccount account) {
        System.out.println(custName);
        System.out.println(account.toString());
        //account_status没设置，balances两个没设置
        boolean success = accountService.add(custName, account);
        if (success) {

            return BankResult.ok();
        } else {
            BankResult bankResult = new BankResult();
            String msg = "创建账户失败";
            bankResult.setMsg(msg);
            bankResult.setStatus(123);
            return bankResult;
        }
    }



}
