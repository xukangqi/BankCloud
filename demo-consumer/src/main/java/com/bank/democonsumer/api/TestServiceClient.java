package com.bank.democonsumer.api;

import com.bank.democonsumer.pojo.BankUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("demoProvider")
public interface TestServiceClient {
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
