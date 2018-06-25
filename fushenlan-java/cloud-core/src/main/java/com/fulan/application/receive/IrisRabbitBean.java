package com.fulan.application.receive;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 消息队列bean定义
 * @author: guiyang
 * @date: 2018/1/23 14:01
 */
@Configuration
public class IrisRabbitBean {

    @Bean
    public Queue queueIrisMessages() {
        return new Queue(RabbitmqConst.QUEUE_TOPIC_IRIS_MESSAGE);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitmqConst.IRIS_EXCHANGE_TOPIC);
    }

    /**
     * 将队列topic.message与exchange绑定
     * @return
     */
    @Bean
    Binding bindingIrisExchangeMessage() {
        return BindingBuilder
                .bind(queueIrisMessages())
                .to(exchange())
                .with(RabbitmqConst.QUEUE_TOPIC_IRIS_MESSAGE_RULE);
    }

}