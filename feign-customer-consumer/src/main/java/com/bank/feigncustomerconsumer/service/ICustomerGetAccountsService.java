package com.bank.feigncustomerconsumer.service;

import com.bank.feigncustomerconsumer.service.hystrix.CustomerGetAccountsHystrix;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "provider-customer-getaccounts", fallback = CustomerGetAccountsHystrix.class)
public interface ICustomerGetAccountsService {
    @RequestMapping(value = "/customer/account/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getAccounts(@PathVariable String custId);
}
