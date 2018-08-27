/**
 * 
 */
package com.zwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 黄记新
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.zwo.**.mapper")
public class SpringEurekaSystemApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaSystemApplication.class, args);
	}

}
