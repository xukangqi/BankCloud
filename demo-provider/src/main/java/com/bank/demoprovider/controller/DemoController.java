package com.bank.demoprovider.controller;

import com.bank.demoprovider.pojo.BankUser;
import com.bank.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(){
        return demoService.hello();
    }

    @GetMapping("/test/database")
    public BankUser test(){
        return demoService.test();
    }
}
