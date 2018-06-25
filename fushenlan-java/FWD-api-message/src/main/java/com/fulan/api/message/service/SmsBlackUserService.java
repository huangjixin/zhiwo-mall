package com.fulan.api.message.service;


import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.vo.SmsBlackUserVO;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @Description: 短信手机号码黑名单API接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsBlackUserService {


    /**
     * 查询手机号码黑名单列表
     */
    @PostMapping("/manage/smsBlackUser/selectSmsBlackUser")
    PageInfo<SmsBlackUser> selectSmsBlackUser(@RequestBody SmsBlackUserVO smsBlackUserVO);
    /**
     * 批量插入手机号码黑名单
     */
    @PostMapping("/manage/smsBlackUser/saveSmsBlackUser")
    Response<String> saveSmsBlackUser(@RequestBody List<SmsBlackUser> smsBlackUserList);

    /**
     * 批量移除手机号码黑名单
     */
    @PostMapping("/manage/smsBlackUser/deleteBatchSmsBlackUser")
    Response<String> deleteBatchSmsBlackUser(@RequestBody List<Long> ids);

    /**
     * 批量移除手机号码黑名单
     */
    @PostMapping("/manage/smsBlackUser/deleteSmsBlackUserById")
    Response<String> deleteSmsBlackUserById(@RequestParam("id") Long id);
    
    
    /**
     * 查询手机号码黑名单列表
     * @param type 
     */
    @GetMapping("/manage/smsBlackUser/selectSmsList")
	PageInfo<SmsBlackUser> selectSmsList(@RequestParam(value="phone",required=false) String phone,
            @RequestParam(value="type",required=false)  String type, @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);
    

}
