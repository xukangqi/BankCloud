package com.bank.ServiceFundNormal.api;

import com.bank.ServiceFundNormal.pojo.BankAccount;
import com.bank.ServiceFundNormal.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
