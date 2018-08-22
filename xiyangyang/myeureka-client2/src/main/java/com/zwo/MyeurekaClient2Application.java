package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
@EnableFeignClients
public class MyeurekaClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyeurekaClient2Application.class, args);
	}

}
