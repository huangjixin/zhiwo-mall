package com.zwotech.common.utils;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void removeRedisKey(RedisTemplate redisTemplate,String key){
		if (redisTemplate != null) {
			if (redisTemplate.hasKey(key)) {
				redisTemplate.delete(key);
			}
		}
	}
}
