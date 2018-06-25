package com.fulan.application;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.fulan.application.orm.tenant.TenantEntityHolder;
import com.fulan.application.orm.tenant.TenantSqlParser;

@Configuration
@MapperScan("com.fulan.application.mapper")
public class MybatisConfig {
	
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
        List<ISqlParser> sqlParserList = new ArrayList<>();
        //TenantSqlParser tenantSqlParser = new TenantSqlParser();
        //sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }
    
    @PostConstruct
    public void initTenantEntityHolder() {
    	TenantEntityHolder.scan(new String[] {"com.fulan.application.domain"});
    }


}
