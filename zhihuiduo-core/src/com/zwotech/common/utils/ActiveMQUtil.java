/**
 * 
 */
package com.zwotech.common.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSONObject;

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
			final String message) {
		// 使用MessageConverter的情况
//		jmsQueueTemplate.convertAndSend(queueName, message);
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	public static Object parseJsonToObject(JSONObject obj, Object o) {  
        StringBuilder sb = new StringBuilder();  
        Class<? extends Object> c = o.getClass();  
        try {  
            c.newInstance();  
            Field[] fs = c.getDeclaredFields();  
            Method[] ms = c.getDeclaredMethods();  
            for (Field f : fs) {  
                if (obj.containsKey(f.getName())) {  
                    for (Method m : ms) {  
                        if (m.getName().equals(sb.append("set").append(f.getName().substring(0, 1).toUpperCase())  
                                .append(f.getName().substring(1)).toString())) {  
                              
                                f.setAccessible(true);  
                                if (f.getType().getName().equals("java.lang.String")) {  
                                    f.set(o, obj.get(f.getName()));  
                                }else{  
                                    f.set(o, Integer.valueOf((String) obj.get(f.getName())));  
                                }  
                        } else {  
                            sb = null;  
                            sb = new StringBuilder();  
                        }  
                    }  
                }  
            }  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
}
