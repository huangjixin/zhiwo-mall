package com.zwo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zwo.modules.system.filter.JWTFilter;
import com.zwo.modules.system.security.ShiroSecurityRealm;

//@Configuration
public class ShiroConfiguration {
	@Bean("securityManager")
	public DefaultWebSecurityManager getManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		// 使用自己的realm
		manager.setRealm(new ShiroSecurityRealm());
		/**
		 * 关闭shiro自带的session,详情见文档 /2014th7cj/d/file/p/20171028/session-management.html
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		manager.setSubjectDAO(subjectDAO);
		return manager;
	}

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<>();
		filterMap.put("jwt", new JWTFilter());
		factoryBean.setFilters(filterMap);
		factoryBean.setSecurityManager(securityManager);
//		factoryBean.setUnauthorizedUrl("/401");
		Map<String, String> filterRuleMap = new HashMap<>();
//		filterRuleMap.put("/edit", "jwt, perms[edit]");
//		filterRuleMap.put("/admin/**", "jwt, roles[admin]");
		filterRuleMap.put("/**", "anon");
		factoryBean.setFilterChainDefinitionMap(filterRuleMap);
		return factoryBean;
	}
}
