package com.bank.feigncustomerconsumer.service;


import com.bank.feigncustomerconsumer.pojo.BankCustomer;
import com.bank.feigncustomerconsumer.service.hystrix.CustomerAddCustomerHystrix;
import com.bank.feigncustomerconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "provider-customer-addcustomer", fallback = CustomerAddCustomerHystrix.class)
public interface ICustomerAddCustomerService {
    @RequestMapping(value = "/customer/new", method = RequestMethod.POST)
    @ResponseBody
    public BankResult addCustomer(BankCustomer customer);
}
