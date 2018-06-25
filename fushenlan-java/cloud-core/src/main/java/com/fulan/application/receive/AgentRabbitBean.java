package com.fulan.application.receive;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 消息队列bean定义
 * @author: guiyang
 * @date: 2018/1/23 14:01
 */
@Configuration
public class AgentRabbitBean {

    @Bean
    public Queue queueAgentMessages() {
        return new Queue(RabbitmqConst.QUEUE_TOPIC_ER_AGENT);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitmqConst.AGENT_EXCHANGE_TOPIC);
    }

    /**
     * 将队列topic.message与exchange绑定
     * @return
     */
    @Bean
    Binding bindingAgentExchangeMessage() {
        return BindingBuilder
                .bind(queueAgentMessages())
                .to(exchange())
                .with(RabbitmqConst.QUEUE_TOPIC_ER_AGENT_RULE);
    }

}