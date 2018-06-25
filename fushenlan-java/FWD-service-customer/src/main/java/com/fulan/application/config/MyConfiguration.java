package com.fulan.application.config;

import com.codingapi.tx.springcloud.feign.TransactionRestTemplateInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by lorne on 2017/6/28.
 */

public class MyConfiguration {


    @Bean
    public RequestInterceptor requestInterceptor(){
        return new TransactionRestTemplateInterceptor();
    }
}
