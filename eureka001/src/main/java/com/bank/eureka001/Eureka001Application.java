package com.bank.eureka001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka001Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka001Application.class, args);
    }

}

