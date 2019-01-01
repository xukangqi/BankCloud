package com.bank.feigncustomerconsumer.controller;

import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.ICustomerOtherService;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerOtherController {
    @Autowired
    private ICustomerOtherService customerOtherService;

    // 返回所有客户信息（已测试）
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllCustomer() {
        return customerOtherService.getAllCustomer();
    }


    // 修改客户信息（已测试）
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public BankResult updateAccount(BankCustomer customer) {
        return customerOtherService.updateAccount(customer);
    }


    // 删除一个账户（已测试）
    @RequestMapping(value = "/account/delete", method = RequestMethod.POST)
    @ResponseBody
    public BankResult deleteAccount(@RequestParam(value = "account") String account) {
        return customerOtherService.deleteAccount(account);
    }
}
