package com.bank.withdrawaddservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.bank.withdrawaddservice.dao")
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.bank.withdrawaddservice.api"})
public class WithdrawAddServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithdrawAddServiceApplication.class, args);
    }

}

