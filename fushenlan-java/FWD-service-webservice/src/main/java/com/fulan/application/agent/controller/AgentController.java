package com.fulan.application.agent.controller;

import com.fulan.api.agent.vo.Req;
import com.fulan.api.agent.vo.ResultBranchs;
import com.fulan.api.agent.vo.ResultCustomer;
import com.fulan.api.agent.vo.ResultModel;
import com.fulan.api.agent.vo.VTag;
import com.fulan.application.service.AgentService;
import com.fulan.application.service.FlagService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@NoLog
@Api(tags = "agent", description = "代理人信息查询")
@RestController
@RequestMapping(value = "/agentServiceFromDms", produces = { "application/json;charset=utf-8" })
public class AgentController {

	@Autowired
	AgentService agentService;

	@Autowired
	FlagService flagService;

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

	@ApiOperation(value = "代理人信息查询", notes = "代理人信息", response = Response.class)
	@RequestMapping(value = "/getAgentFromDms", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	// @ResponseBody
	public Response<ResultModel> getAgentFromDms(String userId, String token, @RequestBody Req agentInfoVo) {
		try {
			return agentService.getAgent(userId, token, agentInfoVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ResultModel>(Response.ERROR, "查看代理人信息失败");
		}
	}

	@ApiOperation(value = "机构信息查询", notes = "机构信息", response = Response.class)
	@RequestMapping(value = "/getBranch", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	// @ResponseBody
	public Response<ResultBranchs> getBranch(String userId, String token, @RequestBody Req agentInfoVo) {
		try {
			return agentService.getBranch(userId, token, agentInfoVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ResultBranchs>(Response.ERROR, "查看机构信息失败");
		}
	}

	@ApiOperation(value = "客户信息查询", notes = "客户信息", response = Response.class)
	@RequestMapping(value = "/getCustomerList", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	// @ResponseBody
	public Response<ResultCustomer> getCustomerList(String userId, String token, @RequestBody Req agentInfoVo) {
		try {
			return agentService.getCustomerList(userId, token, agentInfoVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ResultCustomer>(Response.ERROR, "查看客户信息失败");
		}
	}

	@ApiOperation(value = "代理人的标签信息", notes = "代理人的标签信息", response = Object.class)
	@GetMapping(value = "/getFlags")
	@ResponseBody
	public String getFlags(String agentCode) {

		agentCode = agentCode == null ? "10000487" : agentCode;
		return flagService.searchFlagsByAgentCode(agentCode);

	}

	@ApiOperation(value = "添加标签", notes = "添加标签", response = Response.class)
	@PostMapping(value = "/addFlag")
	@ResponseBody
	public String addFlag(@RequestBody VTag vTag) {
		flagService.addFlags(vTag.getAgentCode(), vTag.getName(), vTag.getRule());
		//查询
		String agentCode = vTag.getAgentCode();
		agentCode = agentCode == null ? "10000487" : agentCode;
		return flagService.searchFlagsByAgentCode(agentCode);
}

	@ApiOperation(value = "删除标签", notes = "删除标签", response = Response.class)
	@PostMapping(value = "/delFlag")
	@ResponseBody
	public String delFlag(@RequestBody VTag vTag) {
		// 删除
		flagService.delFlag(vTag.getTagId());

		// 查询
		String agentCode = vTag.getAgentCode();
		agentCode = agentCode == null ? "10000487" : agentCode;
		return flagService.searchFlagsByAgentCode(agentCode);
	}
}
