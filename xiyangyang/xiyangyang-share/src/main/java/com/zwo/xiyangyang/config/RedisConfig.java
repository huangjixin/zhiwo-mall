package com.zwo.xiyangyang.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration  
//@EnableCaching
//@EnableRedisHttpSession
public class RedisConfig{
	
	@Value("${spring.redis.host}")  
    private String host;  
    @Value("${spring.redis.port}")  
    private int port;  
    @Value("${spring.redis.timeout}")  
    private int timeout;  
    @Value("${spring.redis.password}")  
    private String password; 
    
    @Bean  
    public JedisPoolConfig jedisPoolConfig() {
    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    	jedisPoolConfig.setMaxTotal(600);
    	jedisPoolConfig.setMaxIdle(600);
    	jedisPoolConfig.setTestOnBorrow(true);
    	return jedisPoolConfig;
    }
    
    @Bean  
    public JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
    	return jedisConnectionFactory;
    }
    
    @Bean  
    public StringRedisSerializer stringRedisSerializer() {
    	StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    	return stringRedisSerializer;
    }
    
    @Bean  
    public JdkSerializationRedisSerializer jdkRedisSerializer() {
    	JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
    	return jdkSerializationRedisSerializer;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public RedisTemplate redisTemplate() {
    	RedisTemplate redisTemplate = new RedisTemplate();
    	redisTemplate.setConnectionFactory(jedisConnectionFactory());
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
    	redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
    }
    
    
    @Bean
    public RedisCacheManager redisCacheManager() {
    	//RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
    	RedisCacheManager redisCacheManager = RedisCacheManager.create(jedisConnectionFactory());
//    	RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
    	
    	return redisCacheManager;
    }
    
    @Bean
    public CompositeCacheManager compositeCacheManager() {
    	CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
    	compositeCacheManager.setFallbackToNoOpCache(true);
    	
    	List<CacheManager> cacheManagers = new ArrayList<CacheManager>();
    	cacheManagers.add(redisCacheManager());
    	compositeCacheManager.setCacheManagers(cacheManagers);
    	return compositeCacheManager;
    }
	/*@SuppressWarnings("unchecked")
	@Bean(name="redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, String> template = new RedisTemplate<>();

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        @SuppressWarnings("rawtypes")
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }

    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {

        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(3000);
        return cacheManager;
    }*/
    
}
