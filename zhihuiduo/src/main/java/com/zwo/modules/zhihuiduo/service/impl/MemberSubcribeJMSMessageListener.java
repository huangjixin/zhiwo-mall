/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

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
				ActiveMQUtil.send(jmsQueueTemplate, ChannelContance.MEMBER_UPDATE_QUEUE_CHANNEL, member);
/*
				RedisUtil
						.publish(
								jmsTemplate,
								ChannelContance.MEMBER_UPDATE_QUEUE_CHANNEL,
								member);*/
			}
		}
	}

}
