package com.bank.ServiceRemitNormal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.bank.ServiceRemitNormal")
@MapperScan("com.bank.ServiceRemitNormal.dao")
@EnableEurekaClient
//开启feign
@EnableFeignClients
public class ServiceRemitNormalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRemitNormalApplication.class, args);
    }

}

