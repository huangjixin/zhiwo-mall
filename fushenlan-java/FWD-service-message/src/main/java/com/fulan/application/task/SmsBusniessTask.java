package com.fulan.application.task;

import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.system.SmsFactorService;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.core.monitoring.cat.constant.Constant;

import java.util.List;

/**
 * @Description: 批量删除添加手机号码黑名单到缓存
 * @author: guiyang
 * @date: 2018/3/13 12:49
 */
public class SmsBusniessTask implements Runnable{

    private final RedisUtil redisUtil;
    private final Long validTime;

    public SmsBusniessTask(Long validTime) {
        this.redisUtil = SpringUtil.getBean(RedisUtil.class);
        this.validTime = validTime;
    }

    @Override
    public void run() {
        redisUtil.set(Constant.SMS_VALID_TIME ,validTime);
    }
}