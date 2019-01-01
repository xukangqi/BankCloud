package com.bank.bankuser;

		import org.mybatis.spring.annotation.MapperScan;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.bank.bankuser.mapper")  //启动类添加注解扫描
public class BankuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankuserApplication.class, args);
	}

}

