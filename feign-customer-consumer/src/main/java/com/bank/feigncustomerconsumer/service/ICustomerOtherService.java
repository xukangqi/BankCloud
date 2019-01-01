package com.bank.feigncustomerconsumer.service;

import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.hystrix.CustomerOtherHystrix;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider-customer-other", fallback = CustomerOtherHystrix.class)
public interface ICustomerOtherService {

    // 返回所有客户信息（已测试）
    @RequestMapping(value = "/customer/all", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAllCustomer();



    // 修改客户信息（已测试）
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    @ResponseBody
    public BankResult updateAccount(BankCustomer customer);


    // 删除一个账户（已测试）
    @RequestMapping(value = "/customer/account/delete", method = RequestMethod.POST)
    @ResponseBody
    public BankResult deleteAccount(@RequestParam(value = "account") String account);

}
