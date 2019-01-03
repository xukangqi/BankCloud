package com.bank.customergetaccounts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.bank.customergetaccounts.mapper")  //启动类添加注解扫描
public class CustomerGetaccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerGetaccountsApplication.class, args);
    }

}

