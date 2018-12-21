package com.bank.democonsumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demoProvider")
public interface TestServiceClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/hello",
            consumes = "application/json"
    )
    String getHello();
}
