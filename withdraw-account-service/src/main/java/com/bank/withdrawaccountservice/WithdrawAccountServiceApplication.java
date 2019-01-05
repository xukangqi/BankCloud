package com.bank.withdrawaccountservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.bank.withdrawaccountservice.mapper")
@EnableEurekaClient
public class WithdrawAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithdrawAccountServiceApplication.class, args);
    }

}

