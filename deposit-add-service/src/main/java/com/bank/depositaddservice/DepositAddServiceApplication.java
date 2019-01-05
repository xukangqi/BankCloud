package com.bank.depositaddservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.bank.depositaddservice"})
@MapperScan("com.bank.depositaddservice.dao")
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.bank.depositaddservice.api"})
public class DepositAddServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepositAddServiceApplication.class, args);
    }

}

