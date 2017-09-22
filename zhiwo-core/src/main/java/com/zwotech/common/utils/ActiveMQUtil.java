/**
 * 
 */
package com.zwotech.common.utils;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author 黄记新
 *
 */
public class ActiveMQUtil {

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public static void send(JmsTemplate jmsQueueTemplate, String queueName,
			final Serializable message) {
		// 使用MessageConverter的情况
		jmsQueueTemplate.convertAndSend(queueName, message);
		/*jmsQueueTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});*/
	}
}
