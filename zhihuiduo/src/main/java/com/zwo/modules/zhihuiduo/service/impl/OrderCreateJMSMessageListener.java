/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.service.IOrderTradeService;
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
		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				Object obj = objMessage.getObject();
				OrderTrade orderTrade = (OrderTrade) obj;
				OrderTrade orderTrade2 = orderTradeService
						.selectByPrimaryKey(orderTrade.getId());
				if (orderTrade2 == null) {
					orderTradeService.insertSelective(orderTrade);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
