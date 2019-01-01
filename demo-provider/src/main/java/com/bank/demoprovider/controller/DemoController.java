package com.bank.demoprovider.controller;

import com.bank.demoprovider.pojo.BankUser;
import com.bank.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoController {

    @Value("${example.config}")
    private String config;

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(){
        return demoService.hello();
    }

    @GetMapping("/database")
    public BankUser test(){
        return demoService.test();
    }

    @PostMapping("/object")
    public BankUser getObject(@RequestBody BankUser bankUser) {
        System.out.println(bankUser.getAddress());
        return demoService.test();
    }

    @GetMapping("/config")
    public String getConfig(){
        return  config;
    }
}
