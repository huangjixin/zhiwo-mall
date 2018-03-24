package com.zwo.xiyangyang.config;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zwo.xiyangyang.modules.shiro.MyExceptionHandler;
import com.zwo.xiyangyang.modules.shiro.StatelessSessionManager;
import com.zwo.xiyangyang.modules.shiro.service.impl.UserShiroRealm;  
  
/** 
 * Created by Administrator on 2017/12/11. 
 */  
@Configuration  
public class ShiroConfig {  
  
	/************ Redis 配置开始 **************/
    @Value("${spring.redis.shiro.host}")  
    private String host;  
    @Value("${spring.redis.shiro.port}")  
    private int port;  
    @Value("${spring.redis.shiro.timeout}")  
    private int timeout;  
    @Value("${spring.redis.shiro.password}")  
    private String password;  
    @Value("${spring.redis.redis.dbIndex}")  
    private int database;  
    /************ Redis 配置结束 **************/
    @Bean  
    public WebMvcConfigurer corsConfigurer() {  
        return new WebMvcConfigurerAdapter() {  
            @Override  
            public void addCorsMappings(CorsRegistry registry) {  
                registry.addMapping("/**")  
                .allowedOrigins("*")  
              .allowedMethods("PUT", "DELETE","GET","POST")  
                .allowedHeaders("*")  
              .exposedHeaders("access-control-allow-headers",  
                      "access-control-allow-methods",  
                      "access-control-allow-origin",  
                      "access-control-max-age",  
                      "X-Frame-Options")  
              .allowCredentials(false).maxAge(3600);  
            }  
        };  
    
    } 
    @Bean  
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {  
        System.out.println("ShiroConfiguration.shirFilter()");  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(securityManager);  
  
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        //注意过滤器配置顺序 不能颠倒  
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl  
        filterChainDefinitionMap.put("/logout", "logout");  
        // 配置不会被拦截的链接 顺序判断  
        filterChainDefinitionMap.put("/druid/**", "anon"); 
        filterChainDefinitionMap.put("/static/**", "anon");  
        filterChainDefinitionMap.put("/ajaxLogin", "anon");  
        filterChainDefinitionMap.put("/login", "anon");  
        filterChainDefinitionMap.put("/**", "authc");  
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据  
        shiroFilterFactoryBean.setLoginUrl("/unauth");  
        // 登录成功后要跳转的链接  
//        shiroFilterFactoryBean.setSuccessUrl("/index");  
        //未授权界面;  
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    }  
  
    /**  
     * 凭证匹配器  
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了  
     * ）  
     *  
     * @return  
     */  
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;  
    }  
    
    @Bean  
    public UserShiroRealm userShiroRealm() {  
    	UserShiroRealm userShiroRealm = new UserShiroRealm();  
        userShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher()); 
        userShiroRealm.setCachingEnabled(true);
        userShiroRealm.setAuthenticationCachingEnabled(true);
        userShiroRealm.setAuthenticationCacheName("authenticationCache");
        userShiroRealm.setAuthorizationCachingEnabled(true);
        userShiroRealm.setAuthorizationCacheName("authorizationCache");
        return userShiroRealm;
    }
  
    @Bean  
    public SecurityManager securityManager() {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        securityManager.setRealm(userShiroRealm());  
        // 自定义session管理 使用redis  
        securityManager.setSessionManager(sessionManager());  
        // 自定义缓存实现 使用redis  
        securityManager.setCacheManager(cacheManager());  
        return securityManager;  
    }  
  
    //自定义sessionManager  
    @Bean  
    public SessionManager sessionManager() {  
        StatelessSessionManager sessionManager = new StatelessSessionManager(); 
//        sessionManager.setGlobalSessionTimeout(1800);
//        sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(getSessionIdCookie());
        sessionManager.setSessionDAO(redisSessionDAO());  
        return sessionManager;  
    }  
  
    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(1000);
        return scheduler;
    }
    
    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);//30天
        return simpleCookie;
    }


    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("shiroSessionId");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        return cookie;
    }


    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager getCookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager =
                new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(
                org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(getRememberMeCookie());
        return cookieRememberMeManager;
    }
    
    /** 
     * 配置shiro redisManager 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    @Bean
    public RedisManager redisManager() {  
        RedisManager redisManager = new RedisManager();  
        redisManager.setHost(host);  
        redisManager.setPort(port);  
        redisManager.setExpire(2592000);//30天 配置缓存过期时间  
        redisManager.setTimeout(timeout);  
//        redisManager.setPassword(password);  
        redisManager.setDatabase(database);
        return redisManager;  
    }  
  
    /** 
     * cacheManager 缓存 redis实现 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    @Bean  
    public RedisCacheManager cacheManager() {  
        RedisCacheManager redisCacheManager = new RedisCacheManager();  
        redisCacheManager.setRedisManager(redisManager());  
        return redisCacheManager;  
    }  
  
    /** 
     * 会话设置不过期。
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 
     * <p> 
     * 使用的是shiro-redis开源插件 
     */  
    @Bean  
    public RedisSessionDAO redisSessionDAO() {  
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO(); 
        redisSessionDAO.setExpire(60);
        redisSessionDAO.setRedisManager(redisManager());  
        return redisSessionDAO;  
    }  
  
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }

    /**
     *在此重点说明这个方法，如果不设置为静态方法会导致bean对象无法注入进来，
     *我被这个问题坑的想死的心都有了，晚上感到4点多
     *我是在这篇博客里找到答案的：
     *http://blog.csdn.net/wuxuyang_7788/article/details/70141812
     */
     @Bean(name = "lifecycleBeanPostProcessor")
     public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
         return new LifecycleBeanPostProcessor();
     }

     @Bean
     public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
         DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
         daap.setProxyTargetClass(true);
         return daap;
     }
     
    /** 
     * 开启shiro aop注解支持. 
     * 使用代理方式;所以需要开启代码支持; 
     * 
     * @param securityManager 
     * @return 
     */  
    /*@Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    }*/  
  
    /** 
     * 注册全局异常处理 
     * @return 
     */  
    @Bean(name = "exceptionHandler")  
    public HandlerExceptionResolver handlerExceptionResolver() {  
        return new MyExceptionHandler();  
    }  
}