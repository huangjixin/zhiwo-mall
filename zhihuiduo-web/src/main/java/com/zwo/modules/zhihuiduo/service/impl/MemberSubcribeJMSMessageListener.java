/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import weixin.popular.api.UserAPI;
import weixin.popular.bean.user.User;

import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.wechat.util.MessageUtil;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.ActiveMQUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class MemberSubcribeJMSMessageListener implements MessageListener {

	private JmsTemplate jmsQueueTemplate;

	@Autowired
	@Lazy(true)
	private IMemberService memberService;

	public MemberSubcribeJMSMessageListener() {
		super();
		if (jmsQueueTemplate == null) {
			jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate");
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			String JsonString = ((TextMessage) message).getText();
			Object obj = JSONObject.parse(JsonString);
			Member memb = new Member();
			try {
				BeanUtils.copyProperties(memb, obj);

				Member member = memberService.selectByOpenId(memb.getOpenId());
				if (member == null) {
					member = new Member();

					member.setId(memb.getId());
					member.setUsername(memb.getUsername());
					member.setPassword(memb.getPassword());
					member.setOpenId(memb.getOpenId());
					memberService.insertSelective(member);
				}
				asycUpdateMember(member);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		} catch (JMSException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 异步获取用户头像等信息。
	 * 
	 * @param member
	 */
	private void asycUpdateMember(final Member member) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				String openId = member.getOpenId();
				String accessToken = MessageUtil.ACCESS_TOKEN;
				User user = UserAPI.userInfo(accessToken, openId);
				if (user != null) {
					member.setIcon(user.getHeadimgurl());
					member.setNickname(user.getNickname());
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("unionid", user.getUnionid());
					jsonObject.put("country", user.getCountry());
					jsonObject.put("city", user.getCity());
					member.setDescription(jsonObject.toJSONString());
					member.setPassword(null);
					memberService.updateByPrimaryKeySelective(member);
				}
			}
		});
	}
}
