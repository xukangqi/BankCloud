package com.bank.feignconsumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient  // 仍需配置到eureka
@SpringBootApplication
@EnableHystrixDashboard
public class FeignConsumer1Application {

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumer1Application.class, args);
	}

}

