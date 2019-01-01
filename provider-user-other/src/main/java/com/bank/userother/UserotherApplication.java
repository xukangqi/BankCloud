package com.bank.userother;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.bank.userother.mapper")  //启动类添加注解扫描
public class UserotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserotherApplication.class, args);
    }

}

