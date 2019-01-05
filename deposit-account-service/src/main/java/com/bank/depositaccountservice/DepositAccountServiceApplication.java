package com.bank.depositaccountservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.bank.depositaccountservice.dao")
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DepositAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepositAccountServiceApplication.class, args);
    }

}

