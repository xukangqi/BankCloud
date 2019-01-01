package com.bank.feignuserconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient  // 仍需配置到eureka
@SpringBootApplication
@EnableHystrixDashboard
public class FeignUserConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignUserConsumerApplication.class, args);
	}

}

