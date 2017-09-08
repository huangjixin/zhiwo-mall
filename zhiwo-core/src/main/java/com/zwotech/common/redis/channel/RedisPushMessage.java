/**
 * 
 */
package com.zwotech.common.redis.channel;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 黄记新
 *
 */
public class RedisPushMessage {
	@SuppressWarnings("rawtypes")
	public static void publish(RedisTemplate redisTemplate, String channel,
			Object message) {
		if(redisTemplate!=null)
		redisTemplate.convertAndSend(channel, message);
	}
}
