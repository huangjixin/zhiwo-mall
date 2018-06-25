package com.fulan.api.message.service;


import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.application.util.domain.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 发送短信
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsBusiness {

    /**
     * 发送短信
     */
    @PostMapping("/sms/business/sendSms")
    Response<String> sendSms(@RequestBody SmsBusinessVO smsBusinessVO);
   
    
    
}