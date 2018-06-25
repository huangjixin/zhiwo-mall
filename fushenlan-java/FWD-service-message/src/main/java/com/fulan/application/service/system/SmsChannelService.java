package com.fulan.application.service.system;


import com.fulan.api.message.domain.SmsChannel;

import java.util.List;

/**
 * @Description:  短信通道接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsChannelService {

    /**
     * 查询短信通道
     */
    List<SmsChannel> selectSmsChannel();

}