package com.fulan.application.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.achievement.vo.BankCardDto;
import com.fulan.application.achievement.vo.VerificationCodeDto;
import com.fulan.application.util.domain.Response;

@RestController
@RequestMapping("/oaCommon")
public class OaCommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(OaCommonController.class);
	
	@PostMapping("/sendVerificationCode")
	public Response<String> sendVerificationCode(@RequestBody VerificationCodeDto vcDto){
		//TODO send to mobileNo
		logger.debug("parameter :" + vcDto.getMobileNo());
		Response<String> response = new Response<String>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		return response;
	}
	
	@PostMapping("/verifyVerificationCode")
	public Response<String> verifyVerificationCode(@RequestBody VerificationCodeDto vcDto){
		String vCode = vcDto.getVerificationCode();
		//TODO
		if(vCode==null || vCode.length()!=4) {
			Response<String> response = new Response<String>(Response.ERROR,"验证码不正确");
			return response;
		}
		Response<String> response = new Response<String>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		return response;
	}

}
