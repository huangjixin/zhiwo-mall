/**
 * Project Name:FWD-service-personnel
 * File Name:ProgressRankingCustomercontroller.java
 * Package Name:com.fulan.application.custom.controller
 * Date:2018年1月31日上午9:31:01
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.custom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.personnel.vo.ProgressRankingVO;
import com.fulan.api.security.service.AccountService;
import com.fulan.application.service.PersonnelService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ClassName:ProgressRankingCustomercontroller Reason: TODO ADD REASON Date:
 * 2018年1月31日 上午9:31:01
 * 
 * @author chen.zhuang
 * @version
 * @since JDK 1.8
 */
@NoLog
@Api(tags = "ProgressRanking", description = "进度排名")
@RestController
@RequestMapping(value = "/customer/progressranking")
public class ProgressRankingCustomercontroller {

	@Autowired
	PersonnelService personnelService;

	@Autowired
	AccountService accountService;

	private static final Logger logger = LoggerFactory.getLogger(ProgressRankingCustomercontroller.class);

	@ApiOperation(value = "代理人月目标", notes = "代理人月目标", response = Response.class)
	@RequestMapping(value = "/findMonthlybyagentCode", method = RequestMethod.GET)
	public Response<ProgressRankingVO> findMonthlybyAgentCode(
			@ApiParam(name = "accountId", value = "代理人Id") @RequestParam(required = false) Long accountId,
			@ApiParam(name = "searchMonth", value = "目标时间") @RequestParam(required = false) Long searchMonth) {
		try {
			Response<ProgressRankingVO> resp = new Response<ProgressRankingVO>(Response.SUCCESS, "查询代理人月目标成功");

			int currentNum = personnelService.selectentryNumbyparams(accountId, searchMonth);
			String countNum = accountService.findByAccountIdandTime(accountId, searchMonth)==null?"0":accountService.findByAccountIdandTime(accountId, searchMonth);

			ProgressRankingVO progressRankingVO = new ProgressRankingVO();
			progressRankingVO.setCurrentNum(currentNum);
			progressRankingVO.setCountNum(countNum);
			resp.setCode("1");
			resp.setData(progressRankingVO);
			return resp;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ProgressRankingVO>(Response.ERROR, "查询代理人月目标失败");
		}
	}

	@ApiOperation(value = "代理人年目标", notes = "代理人年目标", response = Response.class)
	@RequestMapping(value = "/findYeaybyagentCode", method = RequestMethod.GET)
	public Response<ProgressRankingVO> selectYeaytargetbyagentCode(
			@ApiParam(name = "accountId", value = "代理人id") @RequestParam(required = false) long accountId) {
		try {
			Response<ProgressRankingVO> resp = new Response<ProgressRankingVO>(Response.SUCCESS, "查询代理人年目标成功");

			int currentNum = personnelService.selectYeaytargetbyaccountId(accountId);
			String countNum = accountService.selectYeaytargetbyaccountId(accountId)==null?"0":accountService.selectYeaytargetbyaccountId(accountId);

			ProgressRankingVO progressRankingVO = new ProgressRankingVO();
			progressRankingVO.setCurrentNum(currentNum);
			progressRankingVO.setCountNum(countNum);
			resp.setCode("1");
			resp.setData(progressRankingVO);
			return resp;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ProgressRankingVO>(Response.ERROR, "查询代理人年目标失败");
		}
	}

	@ApiOperation(value = "团队月目标", notes = "团队月目标", response = Response.class)
	@RequestMapping(value = "/findteamMonthlybyaccountId", method = RequestMethod.GET)
	public Response<ProgressRankingVO> findteamMonthlybyaccountId(
			@ApiParam(name = "companyId", value = "公司IDcompanyId") @RequestParam(required = false) String companyId,
			@ApiParam(name = "searchMonth", value = "目标时间") @RequestParam(required = false) Long searchMonth) {
		try {

			Response<ProgressRankingVO> resp = new Response<ProgressRankingVO>(Response.SUCCESS, "查询团队月目标成功");

			List<Long> accountIds = accountService.selectaccountIdsbycompanyId(companyId);

			int currentNum = personnelService.selectteamMouthcountbyaccountId(accountIds, searchMonth);
			String countNum = accountService.selectteamMouthtargetbyaccountIds(accountIds, searchMonth)==null?"0":accountService.selectteamMouthtargetbyaccountIds(accountIds, searchMonth);

			ProgressRankingVO progressRankingVO = new ProgressRankingVO();
			progressRankingVO.setCurrentNum(currentNum);
			progressRankingVO.setCountNum(countNum);
			resp.setCode("1");
			resp.setData(progressRankingVO);
			return resp;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ProgressRankingVO>(Response.ERROR, "查询团队月目标失败");
		}
	}

	@ApiOperation(value = "团队年目标", notes = "团队年目标", response = Response.class)
	@RequestMapping(value = "/findteamYeaybyagentCode", method = RequestMethod.GET)
	public Response<ProgressRankingVO> findteamYeaybyaccountId(
			@ApiParam(name = "companyId", value = "公司IDcompanyId") @RequestParam(required = false) String companyId) {
		try {

			Response<ProgressRankingVO> resp = new Response<ProgressRankingVO>(Response.SUCCESS, "查询团队年目标成功");

			List<Long> accountIds = accountService.selectaccountIdsbycompanyId(companyId);

			int currentNum = personnelService.selectteamYeaycountbyaccountId(accountIds);
			String countNum = accountService.selectteamYeaycountbyaccountIds(accountIds)==null?"0":accountService.selectteamYeaycountbyaccountIds(accountIds);

			ProgressRankingVO progressRankingVO = new ProgressRankingVO();
			progressRankingVO.setCurrentNum(currentNum);
			progressRankingVO.setCountNum(countNum);
			resp.setCode("1");
			resp.setData(progressRankingVO);
			return resp;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<ProgressRankingVO>(Response.ERROR, "查询团队年目标失败");
		}
	}

	@ApiOperation(value = "进度排名", notes = "进度排名", response = Response.class)
	@RequestMapping(value = "/rankingbyaccountId", method = RequestMethod.GET)
	public Response<String> rankingbyaccountId(
			@ApiParam(name = "companyId", value = "公司IDcompanyId") @RequestParam(required = false) String companyId,
			@ApiParam(name = "accountId", value = "代理人id") @RequestParam(required = false) long accountId) {
		try {

			Response<String> resp = new Response<String>(Response.SUCCESS, "进度排名获取成功");

			List<Long> accountIds = accountService.selectaccountIdsbycompanyId(companyId);
			String str = "0";
			if(accountIds.size()>0){
				 str = personnelService.rankingbyaccountId(accountIds, accountId);
			}
			resp.setCode("1");
			resp.setData(str);
			return resp;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "进度排名获取失败");
		}
	}

}
