/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwotech.common.utils.ActiveMQUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class OrderCreateJMSMessageListener implements MessageListener {

	private JmsTemplate jmsQueueTemplate;

	@Autowired
	@Lazy(true)
	private IOrderTradeService orderTradeService;

	public OrderCreateJMSMessageListener() {
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
			OrderTrade orderTrade = new OrderTrade();
			try {
				BeanUtils.copyProperties(orderTrade, obj);
				
				OrderTrade orderTrade2 = orderTradeService
						.selectByPrimaryKey(orderTrade.getId());
				if (orderTrade2 == null) {
					orderTradeService.insertSelective(orderTrade);
				}
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}

}
