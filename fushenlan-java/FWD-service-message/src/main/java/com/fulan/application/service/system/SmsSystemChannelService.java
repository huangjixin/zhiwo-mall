package com.fulan.application.service.system;


import com.fulan.api.message.domain.SmsSystemChannel;
import com.fulan.api.message.vo.SmsSystemChannelVO;
import com.fulan.application.util.page.PageInfo;

/**
 * @Description: 短信通道规则管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsSystemChannelService {

    /**
     * 查询短信通道规则
     */
    SmsSystemChannel selectSmsSystemChannel();

    /**
     * 插入短信通道规则
     */
     void saveSmsSystemChannel(SmsSystemChannel smsSystemChannel);

    /**
     * 修改短信通道规则
     */
    void updateSmsSystemChannel(SmsSystemChannel smsSystemChannel);

    /**
     * 删除短信通道规则
     */
     void deleteSmsSystemChannelById(Long id);
}