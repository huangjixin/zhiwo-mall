/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class MemberSubcribeMessageListener implements MessageListener {

	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	@Lazy(true)
	private IMemberService memberService;

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
			Map<String, String> map = (Map) body;

			String openId = map.get("openId");
			String id = map.get("id");
			String username = map.get("username");
			String password = map.get("password");
			if (body != null && !"".equals(body)) {
				Member member = memberService.selectByOpenId(openId);
				if (member == null) {
					member = new Member();

					member.setId(id);
					member.setUsername(username);
					member.setPassword(password);
					member.setOpenId(openId);
					memberService.insertSelective(member);

					RedisUtil
							.publish(
									redisTemplate,
									ChannelContance.MEMBER_UPDATE_QUEUE_CHANNEL,
									member);
				}
			}

		}

	}

	public static void main(String[] args) {
		String path = MemberSubcribeMessageListener.class.getResource("/")
				.getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
