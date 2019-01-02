package com.bank.customerservice.controller;

import com.bank.customerservice.pojo.BankAccount;
import com.bank.customerservice.pojo.BankCustomer;
import com.bank.customerservice.service.IAccountService;
import com.bank.customerservice.service.ICustomerService;
import com.bank.customerservice.utils.BankResult;
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


    // 返回所有客户信息（已测试）
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllCustomer() {
        List<BankCustomer> customerList = customerService.getCustomers();
        BankResult bankResult = new BankResult();
        bankResult.setStatus(200);
        bankResult.setData(customerList);
        return bankResult;
    }


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

    // 修改客户信息（已测试）
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public BankResult updateAccount(@RequestBody BankCustomer customer) {
        //密码没md5
        boolean flag = customerService.update(customer);
        if (flag == true) {
            return BankResult.ok();
        } else {
            BankResult bankResult = new BankResult();
            String msg = "修改失败，请检查客户信息是否正确";
            bankResult.setMsg(msg);
            bankResult.setStatus(123);
            return bankResult;
        }
    }



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

    // 删除一个账户（已测试）
    @RequestMapping(value = "/account/delete", method = RequestMethod.POST)
    @ResponseBody
    public BankResult deleteAccount(@RequestParam(value = "account") String account) {
        boolean success = accountService.delete(account);
        if (success) {
            return BankResult.ok();
        } else {
            BankResult bankResult = new BankResult();
            String msg = "删除账户失败";
            bankResult.setMsg(msg);
            bankResult.setStatus(123);
            return bankResult;
        }
    }

    // 验证account
    @RequestMapping(value = "/account/verify", method = RequestMethod.POST)
    @ResponseBody
    public BankResult verify(@RequestParam(value = "accountId") String accountId, @RequestParam(value = "password") String password) {
        boolean is = accountService.verify(accountId, password);
        if (is == true) {
            return BankResult.ok();
        } else {
            BankResult bankResult = new BankResult();
            String msg = "账户验证错误";
            bankResult.setData(msg);
            bankResult.setStatus(123);
            return bankResult;
        }
    }


}
