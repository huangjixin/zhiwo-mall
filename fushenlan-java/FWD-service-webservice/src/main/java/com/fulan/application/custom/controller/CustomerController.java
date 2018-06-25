/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerController.java
 * Package Name:com.fulan.application.custom.controller
 * Date:2018年4月9日上午10:19:07
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.custom.controller;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.application.service.CustomerFamilyService;
import com.fulan.application.service.CustomerService;
import com.fulan.application.service.ProposalService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.util.JsonMsgBean;
import com.fulan.core.monitoring.log.annotation.NoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * ClassName:CustomerController
 * Date:     2018年4月9日 上午10:19:07
 * @author   chen.zhuang
 * @version
 * @since    JDK 1.8
 */
@NoLog
@Api(tags = "Customer", description = "客户信息")
@RestController
@RequestMapping(value ="/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private  CustomerService customerService;

	@Autowired
	private CustomerFamilyService familyService;
	@Autowired
	private ProposalService proposalService;

	@ApiOperation(value = "根据条件查询个人信息", notes = "根据条件查询个人信息", response = Response.class)
	@RequestMapping(value = "/search",produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	@ResponseBody
	public Response<String> customerSearch(String userId,String token, String systemId,  @RequestBody(required=false) CustomerSearchParm req) {
		try {
			return customerService.customerSearch(userId, systemId, token, req);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"根据条件查询个人信息失败");
		}
	}

	@ApiOperation(value = "修改个人信息", notes = "修改个人信息", response = Response.class)
	@RequestMapping(value = "/update",produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	@ResponseBody
	public Response<String> customerUpdate(String userId,String token, String systemId,  @RequestBody CustomerVo req) {
		try {
			return customerService.customerUpdate(userId, systemId, token, req);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"修改信息失败");
		}
	}
	

	@ApiOperation(value = "插入个人信息(客户回写)", notes = "插入个人信息(客户回写)", response = Response.class)
	@RequestMapping(value = "/create",produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	@ResponseBody
	public Response<String> customerCreate(String userId,String token, String systemId,  @RequestBody CustomerVo req) {
		try {
			return customerService.customerCreate(userId, systemId, token, req);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"插入个人信息失败");
		}
	}



	/**
	 * 获取客户分组列表(A-Z分组)
	 * @Date 20180408
	 * @return
	 */
	@GetMapping("customerGroupList")
	@ResponseBody
	@ApiOperation(
			value="列表模式获取代理人的客户信息",
			notes="通过客户类型获取,默认不传全查客户 ")
	public String customerGroupList(
			@ApiParam(name = "customerType",defaultValue ="0",example = "0",value = "客户类型，A_老客户，B_准客户，C_线上客户")
			@RequestParam(name = "customerType",required = false,defaultValue ="0")Integer customerType,
			@ApiParam(name = "key",value = "搜索关键字",example = "like %customerName% or %mobile% or %others%")
			@RequestParam(name = "key",required = false) String key,
			@ApiParam(name = "flags",value = "标签规则数组",example = "['rule1','rule2']")
			@RequestParam(name = "flags",required = false) List<String> flags

	)

	{
		return customerService.getAgentCustomerGroupList(customerType,null);
	}


	/**
	 * 地图模式获取客户列表信息
	 * @Date 20180408
	 * @return
	 */
	@GetMapping("customerMapList")
	@ResponseBody
	@ApiOperation(
			value="地图模式获取代理人的客户信息",
			notes="通过客户类型获取,默认不传全查客户")
	public String customerMapList(
			@ApiParam(name = "key",value = "搜索关键字",example = "代理人")
			@RequestParam(name = "key",required = false) String key,
			@ApiParam(name = "scope",value = "几公里以内",example = "N公里以内，N代表几公里以内")
			@RequestParam(name = "scope",required = true,defaultValue = "1")Integer scope,
			@ApiParam(name = "longitude",value = "经度",example = "经度")
			@RequestParam(name = "longitude",required = false) String longitude,
			@ApiParam(name = "latitude",value = "经度",example = "纬度")
			@RequestParam(name = "latitude",required = false) String latitude

	)
	{
		return customerService.getCustomerMapGroupList(null);

	}


	@ApiOperation(value = "查询建议书", notes = "查询建议书", response = Response.class)
	@RequestMapping(value = "/selectCustomerProposal",produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	@ResponseBody
	public Response<String> selectCustomerProposal(String customerId,String agentCode) {
		/*try {
			JsonMsgBean jsonMsgBean = proposalService.selectCustomerProposal(customerId,agentCode);
			Response<Object> response = new Response<>(Response.SUCCESS,"查询建议书成功");
			if (JsonMsgBean.SUCCESS_CODE.equals(jsonMsgBean.getCode())){
				response.setData(jsonMsgBean.getData());
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR,"查询建议书失败");
		}*/
		return customerService.getCustomerProposal("proposal.json");

	}

	@ApiOperation(value = "查询家庭树", notes = "查询家庭树", response = Response.class)
	@RequestMapping(value = "/selectCustomerFamily",produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	@ResponseBody
	public Response<String> selectCustomerFamily(String customerId) {
		/*try {
			JsonMsgBean jsonMsgBean = familyService.selectCustomerFamily(customerId);
			Response<Object> response = new Response<>(Response.SUCCESS,"查询家庭树成功");
			if (JsonMsgBean.SUCCESS_CODE.equals(jsonMsgBean.getCode())){
				response.setData(jsonMsgBean.getData());
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR,"查询家庭树失败");
		}*/
		return customerService.getCustomerFamily("family.json");

	}

	
}

