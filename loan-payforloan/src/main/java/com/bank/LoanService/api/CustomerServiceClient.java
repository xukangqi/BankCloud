package com.bank.LoanService.api;

import com.bank.LoanService.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "customer-getonecustomer")
public interface CustomerServiceClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/{customerId}",
            consumes = "application/json"
    )
    public BankResult getOneCustomer(@PathVariable("customerId") String customerId);
}
