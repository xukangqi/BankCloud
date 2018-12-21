package com.bank.democonsumer.service.impl;

import com.bank.democonsumer.api.TestServiceClient;
import com.bank.democonsumer.service.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private TestServiceClient testServiceClient;


    @Override
    @HystrixCommand(fallbackMethod = "errorMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public String hello() {
        return testServiceClient.getHello();
    }

    //如果调用超时调用备用方法
    public String errorMethod(){
         return  "error";
    }
}
