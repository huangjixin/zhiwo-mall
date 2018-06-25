package com.fulan.application.service.system;


import com.fulan.api.message.domain.SmsNewsInfo;

import java.util.List;

/**
 * @Description: 消息详情接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsNewsInfoService {

    void saveSmsNewsInfo(List<SmsNewsInfo> smsNewsInfo);

    void updateSmsNewInfo(SmsNewsInfo smsNewsInfo);

    List<SmsNewsInfo> selectSmsNewsInfo(Long smsId,Long status);

    SmsNewsInfo selectSmsNewsInfo(String phone,String receivePhone);

    Integer selectCount(String phone);

}