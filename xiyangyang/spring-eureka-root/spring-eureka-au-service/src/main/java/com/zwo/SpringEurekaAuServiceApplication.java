package com.zwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.zwo.**.mapper")
public class SpringEurekaAuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaAuServiceApplication.class, args);
	}
}
