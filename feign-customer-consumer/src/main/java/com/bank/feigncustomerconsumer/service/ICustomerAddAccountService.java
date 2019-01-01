package com.bank.feigncustomerconsumer.service;


import com.bank.feigncustomerconsumer.pojo.BankAccount;
import com.bank.feigncustomerconsumer.service.hystrix.CustomerAddAccountHystrix;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "provider-customer-addaccount", fallback = CustomerAddAccountHystrix.class)
public interface ICustomerAddAccountService {

    @RequestMapping(value = "/customer/account/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addAccount(@RequestParam(value = "custName") String custName, BankAccount account);

}
