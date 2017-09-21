/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.member.domain.Member;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class OrderCreateMessageListener implements MessageListener {

	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	@Lazy(true)
	private IOrderTradeService orderTradeService;

	/*
	 * 当竞猜问题新增或者更新的时候，重新生成静态页面。 (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}

		if (redisTemplate != null) {
			RedisSerializer<?> stringSerializer = redisTemplate
					.getStringSerializer();
			RedisSerializer<?> valueSerializer = redisTemplate
					.getDefaultSerializer();

			Object channel = stringSerializer.deserialize(message.getChannel());
			Object body = valueSerializer.deserialize(message.getBody());
			OrderTrade orderTrade = (OrderTrade) body;

			if (orderTrade != null) {
//				String id = UUID.randomUUID().toString().replaceAll("-", "");
//				orderTrade.setId(id);
				OrderTrade orderTrade2 = orderTradeService.selectByPrimaryKey(orderTrade.getId());
				if(orderTrade2!=null){
					orderTradeService.insertSelective(orderTrade);
				}
			}

		}

	}

	public static void main(String[] args) {
		String path = OrderCreateMessageListener.class.getResource("/")
				.getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
