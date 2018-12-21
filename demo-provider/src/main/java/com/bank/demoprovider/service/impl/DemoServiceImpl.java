package com.bank.demoprovider.service.impl;

import com.bank.demoprovider.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl  implements DemoService {

    @Override
    public String hello(){
        return "hello";
    }
}
