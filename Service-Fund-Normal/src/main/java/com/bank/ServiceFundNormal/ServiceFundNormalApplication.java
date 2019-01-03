package com.bank.ServiceFundNormal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.bank.ServiceFundNormal")
@MapperScan("com.bank.ServiceFundNormal.dao")
@EnableEurekaClient
//开启feign
@EnableFeignClients
public class ServiceFundNormalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFundNormalApplication.class, args);
    }

}

