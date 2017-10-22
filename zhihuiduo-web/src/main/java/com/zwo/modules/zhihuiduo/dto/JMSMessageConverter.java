package com.zwo.modules.zhihuiduo.dto;
import java.io.Serializable;  
  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.ObjectMessage;  
import javax.jms.Session;  
  
import org.springframework.jms.support.converter.MessageConverter;  
  
public class JMSMessageConverter implements MessageConverter {  
  
    public Message toMessage(Object object, Session session) {  
        ObjectMessage objectMessage = null;  
        try {  
            objectMessage = session.createObjectMessage((Serializable) object);  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
        return objectMessage;  
    }  
  
    public Object fromMessage(Message message) {  
        ObjectMessage objMessage = null;  
        Serializable serializable = null;  
        try {  
            objMessage = (ObjectMessage) message;  
            serializable = objMessage.getObject();  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
        return serializable;  
    }  
}  