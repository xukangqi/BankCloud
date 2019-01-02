package com.bank.ServiceFundHotspot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.bank.ServiceFundHotspot")
@MapperScan("com.bank.ServiceFundHotspot.dao")
@EnableEurekaClient
public class ServiceFundHotspotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFundHotspotApplication.class, args);
    }

}

