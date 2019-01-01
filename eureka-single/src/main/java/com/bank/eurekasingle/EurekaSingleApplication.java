package com.bank.eurekasingle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaSingleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSingleApplication.class, args);
    }

}

