package com.bank.depositrateservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.bank.depositrateservice")
@EnableEurekaClient
public class DepositRateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepositRateServiceApplication.class, args);
    }

}

