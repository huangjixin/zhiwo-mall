package com.fulan.application.achievement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fulan.api.system.domain.Attachment;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.achievement.service.AchAgentClient;
import com.fulan.application.achievement.vo.AgentGrade;
import com.fulan.application.achievement.vo.AgentHistoryIncomeDetail;
import com.fulan.application.achievement.vo.AgentHistoryIncomeDetailedSubItem;
import com.fulan.application.achievement.vo.CommonQueryRepsonse;
import com.fulan.application.achievement.vo.ErrorMessage;
import com.fulan.application.achievement.vo.MyTeamMember;
import com.fulan.application.achievement.vo.PersonalAchievement;
import com.fulan.application.achievement.vo.QueryAgentHistoryIncomeRequest;
import com.fulan.application.achievement.vo.QueryAgentHistoryIncomeResponse;
import com.fulan.application.achievement.vo.QueryAgentRankAssessmentIndexActualValueRequest;
import com.fulan.application.achievement.vo.QueryAgentRankAssessmentResponse;
import com.fulan.application.achievement.vo.QueryAgentTeamListRequest;
import com.fulan.application.achievement.vo.QueryAgentTeamListResponse;
import com.fulan.application.achievement.vo.QueryAgentTeamListResponse.AgentTeamInformation;
import com.fulan.application.achievement.vo.QueryBasicsActualValueRequest;
import com.fulan.application.achievement.vo.QueryBasicsActualValueResponse;
import com.fulan.application.achievement.vo.RankAssessmentIndicator;
import com.fulan.application.oa.controller.BankCardController;
import com.fulan.application.oa.service.OaAgentClient;
import com.fulan.application.oa.vo.FwdCqRespAgentGroupInfoDto;
import com.fulan.application.oa.vo.OAApplyFormVo;
import com.fulan.application.oa.vo.OaReqParamAgentCodeDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * 业绩控制层
 * 
 * @author Tony
 *
 */

@RestController
@RequestMapping("/achivement")
public class AchievementApiController {
	private static final Logger logger = LoggerFactory.getLogger(AchievementApiController.class);
	@Autowired
	private AchAgentClient achAgentClient;
	
	@Autowired
	private OaAgentClient oaAgentClient;
	/**
	 * 代理人历史收入查询
	 * 
	 * @param queryAgentHistoryIncomeRequest
	 * @return
	 */

	
	@RequestMapping(value = "/queryAgentHistoryIncome", method = RequestMethod.GET)
	public Response<QueryAgentHistoryIncomeResponse> queryAgentHistoryIncome(@Validated QueryAgentHistoryIncomeRequest queryAgentHistoryIncomeRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			String errMsg="";
			for (ObjectError err : result.getAllErrors()) {
				errMsg = err.getDefaultMessage() + ",";
			}
			return new Response<QueryAgentHistoryIncomeResponse>(Response.ERROR,errMsg.substring(0, errMsg.lastIndexOf(",")));
		}
		
		Response<QueryAgentHistoryIncomeResponse> response = null;
		try {
			//设置查询参数
			String queryDate = queryAgentHistoryIncomeRequest.getQueryDate();
			if(StringUtils.isNotBlank(queryDate)) {
				SimpleDateFormat toDateSdf = new SimpleDateFormat("yyyy-MM");
				Date d = toDateSdf.parse(queryDate);
				
				SimpleDateFormat toStrSdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.set(Calendar.DAY_OF_MONTH,1);
				queryAgentHistoryIncomeRequest.setStartDate(toStrSdf.format(c.getTime()));
				
				c.add(Calendar.MONTH,1);
				c.add(Calendar.DAY_OF_MONTH,-1);
				queryAgentHistoryIncomeRequest.setEndDate(toStrSdf.format(c.getTime()));
			}
			//调用 common query接口
			CommonQueryRepsonse<QueryAgentHistoryIncomeResponse> queryAgentHistoryIncomeResponse 
									= achAgentClient.queryAgentHistoryIncomeInfo(queryAgentHistoryIncomeRequest);
			
			String statusCode = queryAgentHistoryIncomeResponse.getStatus().getStatusCode();
			String statusMessage = queryAgentHistoryIncomeResponse.getStatus().getStatusMessage();
			if("01".equals(statusCode)) {
				response = new Response<QueryAgentHistoryIncomeResponse>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
				response.setData(queryAgentHistoryIncomeResponse.getResponse());
			}else {
				response = new Response<QueryAgentHistoryIncomeResponse>(Response.ERROR,statusMessage);
			}
		}catch(Exception e) {
			logger.error("server Error", e);
			 response = new Response<QueryAgentHistoryIncomeResponse>(Response.ERROR,e.getMessage());
			return response;
		}
		
		return response;
		
//		List<AgentHistoryIncomeDetailedSubItem> subList = new ArrayList<AgentHistoryIncomeDetailedSubItem>();
//		subList.add(new AgentHistoryIncomeDetailedSubItem("新人引荐奖金","10000"));
//		subList.add(new AgentHistoryIncomeDetailedSubItem("团队育成奖金","20000"));
//		subList.add(new AgentHistoryIncomeDetailedSubItem("年度直接管理奖金","10000"));
//		
//		// 代理人历史收入响应参数 明细信息实体类
//		List<AgentHistoryIncomeDetail> detailLsit = new ArrayList<AgentHistoryIncomeDetail>();
//		detailLsit.add(new AgentHistoryIncomeDetail("佣金类收入","200000",subList));
//		detailLsit.add(new AgentHistoryIncomeDetail("新人持续发展奖","100000",subList));
//		detailLsit.add(new AgentHistoryIncomeDetail("个人销售收入","200000",subList));
//		detailLsit.add(new AgentHistoryIncomeDetail("团队管理奖金","150000",subList));
//		detailLsit.add(new AgentHistoryIncomeDetail("增员与团队发展奖金","210000",subList));
//		detailLsit.add(new AgentHistoryIncomeDetail("激励MDRT达成","100000",subList));
//		
//		QueryAgentHistoryIncomeResponse queryAgentHistoryIncomeResponse = new QueryAgentHistoryIncomeResponse();
//		queryAgentHistoryIncomeResponse.setAfterTax("14000");
//		queryAgentHistoryIncomeResponse.setPreTax("20000");
//		queryAgentHistoryIncomeResponse.setTaxAmount("3000");
//		queryAgentHistoryIncomeResponse.setDetailList(detailLsit);
//		
//		Response<QueryAgentHistoryIncomeResponse> response = new Response<QueryAgentHistoryIncomeResponse>(Response.SUCCESS,
//				Response.SUCCESS_MESSAGE);
//		response.setData(queryAgentHistoryIncomeResponse);
//		return response;
	}

//	 复深蓝的附件service
	@Autowired
	private AttachmentService attService;
	
	/**
	 * 基础业绩实际值查询接口
	 * 
	 * @param queryAgentHistoryIncomeRequest
	 * @return
	 */
	@RequestMapping(value = "/queryBasicsActualValue", method = RequestMethod.GET)
	public Response<QueryBasicsActualValueResponse> queryBasicsActualValue(@Validated QueryBasicsActualValueRequest queryBasicsActualValueRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			String errMsg="";
			for (ObjectError err : result.getAllErrors()) {
				errMsg = err.getDefaultMessage() + ",";
			}
			return new Response<QueryBasicsActualValueResponse>(Response.ERROR,errMsg.substring(0, errMsg.lastIndexOf(",")));
		}
		
		Response<QueryBasicsActualValueResponse> response = null;
		try {
			CommonQueryRepsonse<QueryBasicsActualValueResponse> queryBasicsActualResponse 
									= achAgentClient.queryAgentAchievementInfo(queryBasicsActualValueRequest);

			//start 头像获取
			if (queryBasicsActualResponse != null && queryBasicsActualResponse.getResponse() != null
					&& CollectionUtils.isNotEmpty(queryBasicsActualResponse.getResponse().getGroupList())) {
				for (MyTeamMember member : queryBasicsActualResponse.getResponse().getGroupList()) {
					if (StringUtils.isNotBlank(member.getAgentCode())) {
						List<Attachment> attachmentList = attService.selectByHostId(member.getAgentCode());

						if (CollectionUtils.isNotEmpty(attachmentList)) {
							member.setHeadImageUrl(attachmentList.get(0).getPath());
						}
					}
				}
			}
			//end 头像获取
			
			
			String statusCode = queryBasicsActualResponse.getStatus().getStatusCode();
			String statusMessage = queryBasicsActualResponse.getStatus().getStatusMessage();
			if("01".equals(statusCode)) {
				response = new Response<QueryBasicsActualValueResponse>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
				response.setData(queryBasicsActualResponse.getResponse());
			}else {
				response = new Response<QueryBasicsActualValueResponse>(Response.ERROR,statusMessage);
			}
		}catch(Exception e) {
			logger.error("server Error", e);
			response = new Response<QueryBasicsActualValueResponse>(Response.ERROR,e.getMessage());
			return response;
		}
		
		return response;
		
//		QueryBasicsActualValueResponse queryBasicsActualValueResponse = new QueryBasicsActualValueResponse();
//		String type = queryBasicsActualValueRequest.getGroupType();
//		String agentCode = queryBasicsActualValueRequest.getAgentCode();
//		if("1".equals(type)) {
//			queryBasicsActualValueResponse.setAgentGrade("DD");
//			queryBasicsActualValueResponse.setFyc("11705");
//			queryBasicsActualValueResponse.setFyp("337650000");
//			queryBasicsActualValueResponse.setCaseNo(35);
//			
//			PersonalAchievement personalAchievement = new PersonalAchievement();
//			personalAchievement.setActiveNo(41);
//			personalAchievement.setFyc(238001);
//			personalAchievement.setEffectiveNo(111);
//			personalAchievement.setBredNo(29);
//			personalAchievement.setRecruitNo(61);
//			personalAchievement.setK1Rate(91);
//			
//			queryBasicsActualValueResponse.setPersonalAchievement(personalAchievement);
//		}else {
//			
//			queryBasicsActualValueResponse.setAgentGrade("DD");
//			queryBasicsActualValueResponse.setFyc("21705");
//			queryBasicsActualValueResponse.setFyp("537650000");
//			queryBasicsActualValueResponse.setCaseNo(4);
//			
//			PersonalAchievement personalAchievement = new PersonalAchievement();
//			personalAchievement.setActiveNo(40);
//			personalAchievement.setFyc(238000);
//			personalAchievement.setEffectiveNo(110);
//			personalAchievement.setBredNo(28);
//			personalAchievement.setRecruitNo(60);
//			personalAchievement.setK1Rate(90);
//			
//			List<MyTeamMember> list = new ArrayList<MyTeamMember>();
//			if("000000".equals(agentCode)) {
//				list.add(new MyTeamMember("000001","张三","AM",1,2,2,5,""));
//				list.add(new MyTeamMember("000002","李四","AM",2,1,2,3,""));
//				list.add(new MyTeamMember("000003","袁华","SAM",3,2,1,5,""));
//			}else if("000001".equals(agentCode)||"000002".equals(agentCode)||"000003".equals(agentCode)){
//				list.add(new MyTeamMember("000004","陈浩天","SSM",1,2,2,5,""));
//				list.add(new MyTeamMember("000005","李赋斌","SD",2,1,2,3,""));
//				list.add(new MyTeamMember("000006","刘天","SSM",3,2,1,5,""));
//			}else {
//				list.add(new MyTeamMember("00005","李权","LA",2,1,2,3,""));
//				list.add(new MyTeamMember("00006","周翔","SM",3,2,1,5,""));
//			}
//			
//			queryBasicsActualValueResponse.setPersonalAchievement(personalAchievement);
//			queryBasicsActualValueResponse.setGroupList(list);
//		}
//		
//		Response<QueryBasicsActualValueResponse> response = new Response<QueryBasicsActualValueResponse>(Response.SUCCESS,
//				Response.SUCCESS_MESSAGE);
//		response.setData(queryBasicsActualValueResponse);
//		return response;
	}

	/**
	 * 代理人职级维持考核指标实际值查询
	 * 
	 * @param queryAgentHistoryIncomeRequest
	 * @return
	 */
	@RequestMapping(value = "/agentRankAssessmentKeep", method = RequestMethod.GET)
	public Response<QueryAgentRankAssessmentResponse> agentRankAssessmentKeep(
			@Validated QueryAgentRankAssessmentIndexActualValueRequest rankRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			String errMsg="";
			for (ObjectError err : result.getAllErrors()) {
				errMsg = err.getDefaultMessage() + ",";
			}
			return new Response<QueryAgentRankAssessmentResponse>(Response.ERROR,errMsg.substring(0, errMsg.lastIndexOf(",")));
		}
		// 代理人职级维持考核指标实际 响应参数
		QueryAgentRankAssessmentResponse queryAgentRankAssessmentResponse = new QueryAgentRankAssessmentResponse();
		queryAgentRankAssessmentResponse.setAssessPeriodFrom("2018/01/01");
		queryAgentRankAssessmentResponse.setAssessPeriodTo("2018/03/31");
		queryAgentRankAssessmentResponse.setNextAssessDay(100);
		queryAgentRankAssessmentResponse.setNextAssessHour(13);
		queryAgentRankAssessmentResponse.setNextAssessMinus(51);
		
		List<RankAssessmentIndicator> indicatorList = new ArrayList<RankAssessmentIndicator>();
		indicatorList.add(new RankAssessmentIndicator("直辖组FYC业绩",533800L,50000L));
		indicatorList.add(new RankAssessmentIndicator("直接育成主管",4L,5L));
		indicatorList.add(new RankAssessmentIndicator("直辖有效人力",4L,5L));
		queryAgentRankAssessmentResponse.setIndicatorList(indicatorList);
		
		Response<QueryAgentRankAssessmentResponse> response = new Response<QueryAgentRankAssessmentResponse>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(queryAgentRankAssessmentResponse);
		return response;
	}

	/**
	 * 代理人职级晋级考核指标实际值查询接口
	 * 
	 * @param queryAgentHistoryIncomeRequest
	 * @return
	 */
	@RequestMapping(value = "/agentRankAssessmentPromotion", method = RequestMethod.GET)
	public Response<QueryAgentRankAssessmentResponse> agentRankAssessmentPromotion(
			@Validated QueryAgentRankAssessmentIndexActualValueRequest rankRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			String errMsg="";
			for (ObjectError err : result.getAllErrors()) {
				errMsg = err.getDefaultMessage() + ",";
			}
			return new Response<QueryAgentRankAssessmentResponse>(Response.ERROR,errMsg.substring(0, errMsg.lastIndexOf(",")));
		}
		// 代理人职级维持考核指标实际 响应参数
		QueryAgentRankAssessmentResponse queryAgentRankAssessmentResponse = new QueryAgentRankAssessmentResponse();
		queryAgentRankAssessmentResponse.setAssessPeriodFrom("2018/01/01");
		queryAgentRankAssessmentResponse.setAssessPeriodTo("2018/03/31");
		queryAgentRankAssessmentResponse.setNextAssessDay(200);
		queryAgentRankAssessmentResponse.setNextAssessHour(14);
		queryAgentRankAssessmentResponse.setNextAssessMinus(50);
		queryAgentRankAssessmentResponse.setAgentGrade(new AgentGrade("LA","寿险顾问"));
		queryAgentRankAssessmentResponse.setPromotionDirect(new AgentGrade[] {new AgentGrade("AM","业务经理"),new AgentGrade("SM","销售经理")});
		
		List<RankAssessmentIndicator> indicatorList = new ArrayList<RankAssessmentIndicator>();
			if("LA-SM".equals(rankRequest.getLine())) {
				indicatorList.add(new RankAssessmentIndicator("直辖组FYC业绩",333800L,50000L));
				indicatorList.add(new RankAssessmentIndicator("直接育成主管",3L,5L));
				indicatorList.add(new RankAssessmentIndicator("直辖有效人力",3L,5L));
			}else {
				indicatorList.add(new RankAssessmentIndicator("直辖组FYC业绩",23800L,50000L));
				indicatorList.add(new RankAssessmentIndicator("直接育成主管",2L,5L));
				indicatorList.add(new RankAssessmentIndicator("直辖有效人力",2L,5L));
			}
		
		queryAgentRankAssessmentResponse.setIndicatorList(indicatorList);
		
		Response<QueryAgentRankAssessmentResponse> response = new Response<QueryAgentRankAssessmentResponse>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(queryAgentRankAssessmentResponse);
		return response;
	}

	/**
	 * 代理人团队列表查询接口
	 * 
	 * @param queryAgentHistoryIncomeRequest
	 * @return
	 */
	@RequestMapping(value = "/queryAgentTeamList", method = RequestMethod.GET)
	public Response<FwdCqRespAgentGroupInfoDto> queryAgentTeamListRequest(
			@Validated QueryAgentTeamListRequest queryAgentTeamListRequest, BindingResult result) {
		if (result.hasErrors()) {
			String errMsg="";
			for (ObjectError err : result.getAllErrors()) {
				errMsg = err.getDefaultMessage() + ",";
			}
			return new Response<FwdCqRespAgentGroupInfoDto>(Response.ERROR,errMsg.substring(0, errMsg.lastIndexOf(",")));
		}
		
		Response<FwdCqRespAgentGroupInfoDto> response = null;
		try {
			
			//调用 common query
			OaReqParamAgentCodeDto param = new OaReqParamAgentCodeDto();
			param.setAgentCode(queryAgentTeamListRequest.getAgentCode());
			
			CommonQueryRepsonse<FwdCqRespAgentGroupInfoDto> queryAgentGroupInfo = 
					oaAgentClient.queryAgentGroupInfo(param);
			
			//返回
			String statusCode = queryAgentGroupInfo.getStatus().getStatusCode();
			String statusMessage = queryAgentGroupInfo.getStatus().getStatusMessage();
			if("01".equals(statusCode)) {
				response = new Response<FwdCqRespAgentGroupInfoDto>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
				response.setData(queryAgentGroupInfo.getResponse());
			}else {
				response = new Response<FwdCqRespAgentGroupInfoDto>(Response.ERROR,statusMessage);
			}
		}catch(Exception e) {
			logger.error("server Error", e);
			 response = new Response<FwdCqRespAgentGroupInfoDto>(Response.ERROR,e.getMessage());
			return response;
		}
		
		return response;
	}
}
