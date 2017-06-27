package com.zwo.modules.mall.service.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.service.IPrductService;  
   
@Component  
public class ProducerServiceImpl{  
   
    private JmsTemplate jmsTemplate;  
      
    public void sendMessage(Destination destination, final String message) {  
        System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(message);  
            }  
        });  
    }   
  
    public JmsTemplate getJmsTemplate() {  
        return jmsTemplate;  
    }   
  
    @Resource  
    public void setJmsTemplate(JmsTemplate jmsTemplate) {  
        this.jmsTemplate = jmsTemplate;  
    }  
   
    

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");//此文件放在SRC目录下
		IPrductService prductService = (IPrductService)context.getBean("prductServiceImpl");
		PrProduct product = new PrProduct();
		product.setId(System.currentTimeMillis()+"");
		int result = prductService.insertSelective(product);
		
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
} 