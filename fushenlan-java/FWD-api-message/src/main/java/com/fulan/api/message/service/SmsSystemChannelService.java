package com.fulan.api.message.service;


import com.fulan.api.message.domain.SmsSystemChannel;
import com.fulan.api.message.vo.SmsSystemChannelVO;
import com.fulan.application.util.page.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 短信通道规则管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsSystemChannelService {

    /**
     * 查询短信通道规则
     */
    @PostMapping("/manage/smsSystemChannel/selectSmsSystemChannel")
    SmsSystemChannel selectSmsSystemChannel();

    /**
     * 插入短信通道规则
     */
     @PostMapping("/manage/smsSystemChannel/saveSmsSystemChannel")
     void saveSmsSystemChannel(@RequestBody SmsSystemChannel smsSystemChannel);

    /**
     * 修改短信通道规则
     */
    @PostMapping("/manage/smsSystemChannel/updateSmsSystemChannel")
    void updateSmsSystemChannel(@RequestBody SmsSystemChannel smsSystemChannel);

    /**
     * 删除短信通道规则
     */
     @PostMapping("/manage/smsSystemChannel/deleteSmsSystemChannelById")
     void deleteSmsSystemChannelById(@RequestParam("id") Long id);
}