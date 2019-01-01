package com.bank.providercustomeraddcustomer.controller;

import com.bank.providercustomeraddcustomer.pojo.BankAccount;
import com.bank.providercustomeraddcustomer.pojo.BankCustomer;
import com.bank.providercustomeraddcustomer.service.IAccountService;
import com.bank.providercustomeraddcustomer.service.ICustomerService;
import com.bank.providercustomeraddcustomer.utils.BankResult;
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



    // 新建一个客户（已测试）
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addCustomer(@RequestBody BankCustomer customer) {
       //初始时要设置加信用 如    customer.setCredit("1");
        String custName = customer.getCustName();
        BankResult bankResult = new BankResult();
        if (customerService.isExist(custName)) {
            String msg = "用户名已存在";
            bankResult.setMsg(msg);
            return bankResult;
        }
        String cust_id = customerService.add(customer);
        bankResult.setData(cust_id);
        bankResult.setMsg("ok");
        bankResult.setStatus(200);
        return bankResult;
    }


}
