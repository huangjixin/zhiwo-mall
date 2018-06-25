package com.fulan.application;

import com.fulan.core.monitoring.cat.annotation.EnableCat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shengchenglong  
 * @since 2018-01-11
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.fulan.api.system","com.fulan.api.message","com.fulan.api.plan","com.fulan.api.security","com.fulan.api.personnel","com.fulan.api.flow","com.fulan.api.paper","com.fulan.api.material","com.fulan.api.plan","com.fulan.api.course","com.fulan.api.calendar","com.fulan.api.information","com.fulan.api.agent.service"})
@EnableEurekaClient
@EnableCat(basePackages = { "com.fulan.application.controller", "com.fulan.application.service.**", "com.fulan.application.mapper" ,"com.fulan.application.custom.controller"
        ,"com.fulan.application.manage.controller"},exclusions = {"com.fulan.application.domain"})
public class WebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
   }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

}