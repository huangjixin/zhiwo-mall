package com.fulan.api.message.service;


import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:  短信参数
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsParameterService {

	@GetMapping("/manage/factor/selectParameter")
	PageInfo<SmsParameterFactor> selectParameter(
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize);

	@PostMapping("/manage/factor/deleteSmsFactor")
	Response<String> deleteSmsFactor(@RequestParam("id") Long id);

	@GetMapping("/manage/factor/selectParameterById")
	SmsParameterFactor selectParameterById(@RequestParam("id") String id);

	
	@PostMapping("/manage/factor/saveSmsFactor")
	Response<String> saveSmsFactor(@RequestBody SmsParameterFactor smsParameterFactor);
	
	@PostMapping("/manage/factor/updateSmsFactor")
	Response<String> updateSmsFactor(@RequestBody SmsParameterFactor smsParameterFactor);
	
	
	
	@PostMapping("/manage/factor/selectSmsFactor")
	Response<Object> selectSmsFactor();

	@GetMapping("/manage/factor/listFactor")
	List<SmsParameterFactor> listFactor();
	
}