package com.bank.ServiceRemitHotspot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.bank.ServiceRemitHotspot")
@MapperScan("com.bank.ServiceRemitHotspot.dao")
@EnableEurekaClient
//开启feign
@EnableFeignClients
public class ServiceRemitHotspotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRemitHotspotApplication.class, args);
    }

}

