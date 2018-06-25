package com.fulan.application.task;

import com.fulan.api.message.domain.SmsChannel;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.system.SmsChannelService;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.core.monitoring.cat.constant.Constant;

import java.util.List;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/13 12:49
 */
public class ChannelTask implements Runnable{

    private final SmsChannelService channelService;
    private final RedisUtil redisUtil;

    public ChannelTask() {
        channelService = SpringUtil.getBean(SmsChannelService.class);
        this.redisUtil = SpringUtil.getBean(RedisUtil.class);
    }

    @Override
    public void run() {
        List<SmsChannel> smsChannels = channelService.selectSmsChannel();
        redisUtil.set(Constant.SMS_CHANNEL,smsChannels);
    }
}