package com.bank.LoanService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.bank.LoanService.dao")
//hystrix
@EnableCircuitBreaker
@EnableEurekaClient
//开启feign
@EnableFeignClients
public class LoanForPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanForPayApplication.class, args);
    }

}

