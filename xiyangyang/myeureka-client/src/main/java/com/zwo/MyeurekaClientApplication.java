package com.zwo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class MyeurekaClientApplication {

	@Value("${language}")
	private String language = "";
	
	public static void main(String[] args) {
		SpringApplication.run(MyeurekaClientApplication.class, args);
	}
	
	@GetMapping("index")
	public String  getIndex() {
		String s = "这是client"+this.language;
		return s;
	}
}
