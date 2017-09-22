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
public class MemberSubcribeUpdateJMSMessageListener implements MessageListener {

	private JmsTemplate jmsQueueTemplate;

	@Autowired
	@Lazy(true)
	private IMemberService memberService;

	public MemberSubcribeUpdateJMSMessageListener() {
		super();
		if (jmsQueueTemplate == null) {
			jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate");
		}
	}

	/**
	 * 取到微信用户的信息，然后更新。
	 * 
	 * @param message
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof Member) {
			Member member = (Member) message;

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
	}

}
