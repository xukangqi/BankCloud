package com.bank.LoanService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demoProvider")
public interface AccountServiceClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/hello",
            consumes = "application/json"
    )
    String getHello();

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/object",
            consumes = "application/json"
    )
    BankUser getObject(BankUser bankUser);
}
