package com.fulan.application.task;

import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.application.common.FactorVO;
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
public class FactorTask implements Runnable{

    private final SmsFactorService smsFactorService;

    public FactorTask() {
        this.smsFactorService = SpringUtil.getBean(SmsFactorService.class);
    }

    @Override
    public void run() {
        List<SmsParameterFactor> factorList = smsFactorService.selectSmsFactor();
        FactorVO.set(factorList);
    }
}