package com.fulan.api.message.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.message.vo.SmsTemplateVO;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * @Description: 消息模板管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsTemplateService {

    /**
     * 查询消息模板
     */
   // PageInfo<SmsTemplate> selectSmsTemplate(SmsTemplateVO smsTemplateVO);

    /**
     * 插入消息模板
     */
	@PostMapping("/manage/smsTemplate/saveSmsTemplate")
	Response<String> saveSmsTemplate( @RequestBody SmsTemplate smsTemplate);

    /**
     * 修改短信通道规则
     */
	@PostMapping("/manage/smsTemplate/updateSmsTemplate")
	Response<String> updateSmsTemplate(SmsTemplate smsTemplate);

    /**
     * 删除短信通道规则
     */
	 @PostMapping("/manage/smsTemplate/deleteSmsTemplateById")
	public Response<String>  deleteSmsTemplateById(@RequestParam("id") Long id);
     
     
     
     /**
      * 查询sms模板列表页
      * @param type 
      */
     @GetMapping("/manage/smsTemplate/selectTemplateList")
     PageInfo<SmsTemplateResultVO> selectTemplateList(
     		@RequestParam(value="title",required=false) String title,
     		@RequestParam(value="code",required=false) String code,
     		@RequestParam(value="type",required=false) String type,
     		@RequestParam(value="masterCode",required=false) String masterCode,
     		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);
     
     
     
     
     /**
      * 查询sms模板列表页
      * @param type 
      */
     @PostMapping("/manage/smsTemplate/selectSmsTemplateById")
     SmsTemplate selectSmsTemplateById(
     		@RequestParam(value="id",required=false) Long id);
     
     
     
}