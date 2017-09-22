/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.jms.Message;
import javax.jms.MessageListener;

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
		if(jmsQueueTemplate == null){
			jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate");
		}
	}
	
	@Override
	public void onMessage(Message message) {
		if (message instanceof Member) {
			Member memb = (Member) message;
			
			Member member = memberService.selectByOpenId(memb.getOpenId());
			if (member == null) {
				member = new Member();

				member.setId(memb.getId());
				member.setUsername(memb.getUsername());
				member.setPassword(memb.getPassword());
				member.setOpenId(memb.getOpenId());
				memberService.insertSelective(member);
				asycUpdateMember(member);
				//ActiveMQUtil.send(jmsQueueTemplate, ChannelContance.MEMBER_UPDATE_QUEUE_CHANNEL, member);
/*
				RedisUtil
						.publish(
								jmsTemplate,
								ChannelContance.MEMBER_UPDATE_QUEUE_CHANNEL,
								member);*/
			}
		}
	}
	
	/**
	 * 异步获取用户头像等信息。
	 * @param member
	 */
	private void asycUpdateMember(final Member member) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				String openId = member.getOpenId();
				String accessToken = "ehBA152ia2KiCO69OO8cCdWPy9qmqwLOQMfL0v-cVFauZyDDH-9HgS10-crP6E_aQe7uBJrUb3_Dn5OYhpjD1Fm46p3O3lZuequQ2CIxJJWXvR2Oq2bxkcWxlmJylfEtZBShABAGIZ";
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
