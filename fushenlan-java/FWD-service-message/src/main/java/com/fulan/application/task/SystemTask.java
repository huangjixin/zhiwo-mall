package com.fulan.application.task;

import com.fulan.api.message.domain.SmsSystem;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.system.SmsSystemService;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.core.monitoring.cat.constant.Constant;

import java.util.List;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/13 12:49
 */
public class SystemTask implements Runnable{

    private final SmsSystemService systemService;
    private final RedisUtil redisUtil;

    public SystemTask() {
        systemService = SpringUtil.getBean(SmsSystemService.class);
        this.redisUtil = SpringUtil.getBean(RedisUtil.class);
    }

    @Override
    public void run() {
        List<SmsSystem> systems = systemService.selectSmsSystem();
        redisUtil.set(Constant.SMS_SYSTEM, systems);
    }
}