package com.fulan.api.message.service;


import com.fulan.api.message.domain.SmsChannel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Description:  短信通道接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsChannelService {

    /**
     * 查询短信通道
     */
    @PostMapping("/manage/smsChannel/selectSmsChannel")
    List<SmsChannel> selectSmsChannel();

}