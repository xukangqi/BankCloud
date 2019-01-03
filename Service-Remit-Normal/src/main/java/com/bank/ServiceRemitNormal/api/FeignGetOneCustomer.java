package com.bank.ServiceRemitNormal.api;

import com.bank.ServiceRemitNormal.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "customer-getonecustomer")
public interface FeignGetOneCustomer {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/{customerId}",
            consumes = "application/json"
    )
    @ResponseBody
    public BankResult getOneCustomer(@PathVariable("customerId") String customerId);
}
