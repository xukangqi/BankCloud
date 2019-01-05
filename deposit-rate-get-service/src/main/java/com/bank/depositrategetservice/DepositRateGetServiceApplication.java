package com.bank.depositrategetservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.bank.depositrategetservice.mapper")
@EnableEurekaClient
public class DepositRateGetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepositRateGetServiceApplication.class, args);
    }

}

