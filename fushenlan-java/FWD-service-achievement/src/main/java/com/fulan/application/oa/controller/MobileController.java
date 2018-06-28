	package com.fulan.application.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.achievement.vo.ErrorMessage;
import com.fulan.application.achievement.vo.MobileNoDto;
import com.fulan.application.oa.domain.FwdOaMobile;
import com.fulan.application.oa.service.IMobileService;
import com.fulan.application.util.domain.Response;

/**
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	
	@Autowired
	private IMobileService mobileService;

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private Response<String> save(@RequestBody MobileNoDto mobileDto) {
		
		String vcCode = mobileDto.getVerificationCode();
		//TODO 验证码校验
		if(vcCode==null || vcCode.length()!=4) {
			Response<String> response = new Response<String>(Response.ERROR,"验证码错误");
			return response;
		}
		try {
			FwdOaMobile mobile = new FwdOaMobile();
			mobile.setAgentCode(mobileDto.getAgentCode());
			mobile.setMobileNo(mobileDto.getMobileNo());
			mobile.setCreateDatetime(new Date());
			mobile.setUpdateDatetime(new Date());
			mobileService.save(mobile);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			Response<String> response = new Response<String>(Response.ERROR,e.getMessage());
			return response;
		}
		
		Response<String> response = new Response<String>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		return response;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(int id) {
		int result = mobileService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private void update(FwdOaMobile mobile) {
		ErrorMessage errorMessage = new ErrorMessage();
		mobile.setUpdateDatetime(new Date());
		try {
		 mobileService.update(mobile);
		} catch (Exception e) {
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdOaMobile> selectAll() {
		List<FwdOaMobile> fwdOaMobileList = mobileService.selectAll();
		return fwdOaMobileList;
	}
	
	/**
	 * 根据ID查询数据
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
	private FwdOaMobile selectById(@PathVariable(value = "id") Integer id) {
		FwdOaMobile oaMobile = mobileService.selectById(id);
		return oaMobile;
	}
	
	/**
	 * 根据代理人编号查询数据
	 * 
	 * @param selectByAgentCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByAgentCode", method = RequestMethod.GET)
	private Response selectByAgentCode(@RequestParam(name = "agentCode", required = true) String agentCode){
		// 调用第三方接口数据
		List<FwdOaMobile> thirdoaMobileList = new ArrayList<>();
		// 第三方模拟数据
		FwdOaMobile mobile = new FwdOaMobile();
		mobile.setAgentCode("evan");
		mobile.setMobileNo("123456");
		mobile.setAgentName("evan");
		thirdoaMobileList.add(mobile);
		FwdOaMobile mobile2 = new FwdOaMobile();
		mobile2.setAgentCode("evan");
		mobile2.setMobileNo("123456789");
		mobile2.setAgentName("evan");
		thirdoaMobileList.add(mobile2);
		// 本地数据库
		List<FwdOaMobile> fwdOaMobileList = mobileService.selectByAgentCode(agentCode);
		List<FwdOaMobile> oaMobileList = null;
		try {
			oaMobileList = mobileService.saveOrUpdate(thirdoaMobileList, fwdOaMobileList, agentCode);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			Response<ErrorMessage> response = new Response<ErrorMessage>(Response.ERROR,
					Response.ERROR_MESSAGE);
			response.setData(errorMessage);
			e.printStackTrace();
			return response;
		}
		Response<List<FwdOaMobile>> response = new Response<List<FwdOaMobile>>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(oaMobileList);
		return response;
	}
}
