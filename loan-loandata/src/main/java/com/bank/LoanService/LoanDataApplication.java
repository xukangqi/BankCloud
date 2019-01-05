package com.bank.LoanService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.bank.LoanService.dao")
@EnableEurekaClient
public class LoanDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanDataApplication.class, args);
    }

}

