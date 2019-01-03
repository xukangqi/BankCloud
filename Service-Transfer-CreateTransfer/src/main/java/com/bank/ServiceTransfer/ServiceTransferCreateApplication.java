package com.bank.ServiceTransfer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.bank.ServiceTransfer")
@MapperScan("com.bank.ServiceTransfer.dao")
@EnableEurekaClient
//开启feign
@EnableFeignClients
public class ServiceTransferCreateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferCreateApplication.class, args);
    }

}

