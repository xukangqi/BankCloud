package com.bank.feigncustomerconsumer.service;

import com.bank.feigncustomerconsumer.service.hystrix.CustomerGetOneHystrix;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "provider-customer-getone", fallback = CustomerGetOneHystrix.class)
public interface ICustomerGetOneService {
    @RequestMapping(value = "/customer/{custID}", method = RequestMethod.GET)
    @ResponseBody
    public BankResult getOneCustomer(@PathVariable String custID);
}
