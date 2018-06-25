package com.fulan.application.receive;

/**
 * @Description: 消息队列常量规则
 * @author: guiyang
 * @date: 2018/1/23 14:00
 */
public class RabbitmqConst {

    /**
     * 代理人
     */
    public final static String QUEUE_TOPIC_ER_AGENT= "account.info.agent";

    /**
     * 代理人漏油规则
     */
    public final static String QUEUE_TOPIC_ER_AGENT_RULE = "*.info.agent";
    /**
     * 代理人交换机
     */
    public final static String AGENT_EXCHANGE_TOPIC = "agent.exchange";

    /**
     * IRIS消息推送
     */
    public final static String QUEUE_TOPIC_IRIS_MESSAGE= "iris.info.message";

    /**
     * IRIS消息推送漏油规则
     */
    public final static String QUEUE_TOPIC_IRIS_MESSAGE_RULE = "iris.info.*";
    /**
     * IRIS消息推送交换机
     */
    public final static String IRIS_EXCHANGE_TOPIC = "iris.exchange";
}