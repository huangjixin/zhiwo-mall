package com.fulan.api.message.service;


import com.fulan.api.message.domain.SmsSystem;
import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.application.util.domain.Response;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:  短信业务系统接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsSystemService {

	  /**
     * 发送短信
     */
    @PostMapping("/sms/system/sendSms")
    Response<String> sendSms(@RequestBody SmsBusinessParameterVO smsBusinessVO);

}