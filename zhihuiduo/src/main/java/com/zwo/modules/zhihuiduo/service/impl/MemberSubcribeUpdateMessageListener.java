/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import weixin.popular.api.UserAPI;
import weixin.popular.bean.user.User;

import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class MemberSubcribeUpdateMessageListener implements MessageListener {

	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	@Lazy(true)
	private IMemberService memberService;

	/*
	 * 取到微信用户的信息，然后更新。
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
			Member member = (Member) body;

			if (member != null) {
				String openId = member.getOpenId();
				String accessToken = "ehBA152ia2KiCO69OO8cCdWPy9qmqwLOQMfL0v-cVFauZyDDH-9HgS10-crP6E_aQe7uBJrUb3_Dn5OYhpjD1Fm46p3O3lZuequQ2CIxJJWXvR2Oq2bxkcWxlmJylfEtZBShABAGIZ";
				User user = UserAPI.userInfo(accessToken, openId);
				if(user!=null){
					member.setIcon(user.getHeadimgurl());
					member.setNickname(user.getNickname());
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("unionid", user.getUnionid());
					jsonObject.put("country", user.getCountry());
					jsonObject.put("city", user.getCity());
					member.setDescription(jsonObject.toJSONString());
					memberService.updateByPrimaryKeySelective(member);
				}
			}

		}

	}

	public static void main(String[] args) {
		String path = MemberSubcribeUpdateMessageListener.class.getResource("/")
				.getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
