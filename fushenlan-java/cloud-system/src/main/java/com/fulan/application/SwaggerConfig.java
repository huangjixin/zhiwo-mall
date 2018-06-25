package com.fulan.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket createRestApi() {
//		ParameterBuilder tokenPar = new ParameterBuilder();  
//	        List<Parameter> pars = new ArrayList<Parameter>();  
//	        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).defaultValue("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpYXQiOjE1MjIyODcxODksInN1YiI6ImFjY291bnQiLCJpc3MiOiJlbGVhcm4iLCJ1c2VyQWNjb3VudCI6IuS4nOaWueS4jei0pSIsInRlbmFudElkIjoxLCJ1c2VySWQiOjk1NzkwMzQyNzE3MjU2NDk5MiwiZXhwIjoxNTIyMjkwNzg5LCJuYmYiOjE1MjIyODcxODl9.-W7214EE3wbJw6thq_WYYEdhR8PjD_MKJR3kSBFAcdM").build();  
//	        pars.add(tokenPar.build());  
            return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fulan.application"))
                .paths(PathSelectors.any())
                .build();
//                .globalOperationParameters(pars);
                /*.groupName("基础数据服务")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(regex("/dictionary"),regex("/organization")))
                .build()
                .apiInfo(apiInfo());*/
  		
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("包含 字典,组织机构等服务")
            .version("1.0")//版本
            .build();
    }
}
