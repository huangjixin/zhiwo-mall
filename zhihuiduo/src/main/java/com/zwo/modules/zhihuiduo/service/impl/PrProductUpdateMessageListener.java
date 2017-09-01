/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.zwo.modules.mall.domain.PrCategory;
import com.zwo.modules.mall.domain.PrProduct;

/**
 * @author Administrator
 *
 */
@Service
public class PrProductUpdateMessageListener implements MessageListener {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/*
	 * 当商品分类新增或者更新的时候，重新生成顶层菜单。 (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		RedisSerializer<?> stringSerializer = redisTemplate.getStringSerializer();
		RedisSerializer<?> valueSerializer = redisTemplate.getDefaultSerializer();
		Object channel = stringSerializer.deserialize(message.getChannel());
		Object body = valueSerializer.deserialize(message.getBody());
		PrProduct product = null;
		if(body instanceof PrProduct){
			product = (PrProduct) body;
			//更新整个商品JSP；
		}
//		System.out.println("主题: " + channel);
//		System.out.println("消息内容的类型是PrCategory？ " + (body instanceof PrCategory));
	}

	
}
