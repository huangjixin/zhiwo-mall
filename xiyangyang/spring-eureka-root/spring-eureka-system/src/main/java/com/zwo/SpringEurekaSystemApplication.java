/**
 * 
 */
package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 黄记新
 *
 */
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.zwo.modules.system.mapper")
public class SpringEurekaSystemApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaSystemApplication.class, args);
	}

}
