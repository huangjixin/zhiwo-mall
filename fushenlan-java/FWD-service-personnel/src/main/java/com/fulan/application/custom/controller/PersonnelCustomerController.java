package com.fulan.application.custom.controller;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.service.FlowActionService;
import com.fulan.api.personnel.domain.Apply;
import com.fulan.api.personnel.domain.Educational;
import com.fulan.api.personnel.domain.FamilyMember;
import com.fulan.api.personnel.domain.InterviewAction;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.service.InterviewActionService;
import com.fulan.api.personnel.vo.ApplyScan;
import com.fulan.api.personnel.vo.PersonnelEducation;
import com.fulan.api.personnel.vo.PersonnelPaperVo;
import com.fulan.api.personnel.vo.PersonnelPool;
import com.fulan.api.personnel.vo.PersonnelVo;
import com.fulan.api.personnel.vo.WorkExperienceVO;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.AgentBranchInfomation;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.AgentBranchInformationService;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ApplyMapper;
import com.fulan.application.mapper.PersonnelMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.ApplyService;
import com.fulan.application.service.EducationalService;
import com.fulan.application.service.FamilyMemberService;
import com.fulan.application.service.PersonnelPaperInfoService;
import com.fulan.application.service.PersonnelService;
import com.fulan.application.service.WorkExperienceService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.message.MessageUtil;
import com.fulan.core.monitoring.cat.constant.Constant;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@NoLog
@Api(tags = "personnel", description = "在线增员-个人信息接口")
@RestController
@RequestMapping(value ="/customer/personnel")
public class PersonnelCustomerController {
	
	@Autowired
	PersonnelService personnelService;
	@Autowired
	PersonnelMapper personnelMapper;
	@Autowired
	PersonnelPaperInfoService personnelPaperInfoService;
	@Autowired
	ApplyService applyService;
	@Autowired
	ApplyMapper applyMapper;
	@Autowired
	EducationalService educationalService;
	@Autowired
	FamilyMemberService familyMemberService;
	@Autowired
	InterviewActionService interviewActionService;
	@Autowired
	WorkExperienceService workExperienceService;
	@Autowired
	IdGenerator idg;
	@Autowired
	AccountService accountService;
	@Autowired
	FlowActionService flowActionService;
	@Autowired
	AgentBranchInformationService agentBranchInformationService;
	@Autowired
	RedisUtil redisUtil;
	

	

	
	private static final Logger logger = LoggerFactory.getLogger(PersonnelCustomerController.class);

	@ApiOperation(value = "新增人才库", notes = "新增人才库", response = Response.class)
	@RequestMapping(value = "/addPersonnelinfo", method = RequestMethod.POST)
	public Response<String> addPersonnelinfo(@ApiParam(name = "personnelVo", value = "人才信息")@RequestBody PersonnelVo personnelVo) {
		try {
			
			return personnelService.saveandtag(personnelVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增人才库失败");
		}
	}
	
	
	
	@ApiOperation(value = "修改人才库信息", notes = "修改人才库信息", response = Response.class)
	@RequestMapping(value = "/updatePersonnelinfo", method = RequestMethod.POST)
	public Response<String> updatePersonnelinfo(@ApiParam(name = "personnelVo", value = "人才信息")@RequestBody PersonnelVo personnelVo) {
		try {
			
			return personnelService.updatepersonnelandtag(personnelVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增人才库失败");
		}
	}
	
	
	
	
	@ApiOperation(value = "新增个人信息", notes = "在线增员-个人信息", response = Response.class)
	@RequestMapping(value = "/addPersonnel", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> addPersonnel(@RequestBody PersonnelVo personnelVo) {
		try {
			return personnelService.save(personnelVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增个人信息失败");
		}
	}
	
	@ApiOperation(value = "新增家庭信息", notes = "在线增员-家庭信息", response = Response.class)
	@RequestMapping(value = "/addFamilyMember", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> addFamilyMember(@RequestBody List<FamilyMember> familyMemberList) {
		try {
			return familyMemberService.save(familyMemberList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增家庭信息失败");
		}
	}
	
	@ApiOperation(value = "新增教育信息", notes = "在线增员-教育信息", response = Response.class)
	@RequestMapping(value = "/addEducational", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> addEducational(@RequestBody List<Educational> educationalList) {
		try {
			return educationalService.save(educationalList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增教育信息失败");
		}
	}
	@ApiOperation(value = "新增工作信息", notes = "在线增员-工作信息", response = Response.class)
	@RequestMapping(value = "/addWorkExperience", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> addWorkExperience(@RequestBody WorkExperienceVO workExperienceVO) {
		try {
			Personnel personnel = new Personnel();
			personnel.setId(workExperienceVO.getPersonnelId());
			personnel.setIsInsuranceCompany(workExperienceVO.getIsInsuranceCompany());
			personnel.setIsOverSix(workExperienceVO.getIsOverSix());;
			personnel.setNativeWorkTime(workExperienceVO.getNativeWorkTime());
			personnel.setIsQuit(workExperienceVO.getIsQuit());
			personnel.setOriginalCompany(workExperienceVO.getOriginalCompany());
			personnel.setDepartureDate(workExperienceVO.getDepartureDate());
			return workExperienceService.save(workExperienceVO.getWorkExperience(),personnel);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增工作信息失败");
		}
	}
	@ApiOperation(value = "新增计划申请信息", notes = "在线增员-计划申请信息", response = Response.class)
	@RequestMapping(value = "/addApply", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> addApply(@RequestBody Apply apply) {
		try {
			return applyService.save(apply.getPersonnelId(),apply);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增计划申请信息失败");
		}
	}
	@ApiOperation(value = "查看个人信息", notes = "人才详情-个人信息", response = Response.class)
	@RequestMapping(value = "/getPersonnel", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Personnel> getPersonnel(@RequestParam Long personnelId) {
		try {
			return personnelService.getPersonnel(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Personnel>(Response.ERROR,"查看个人信息失败");
		}
	}
	@ApiOperation(value = "查看个人信息-教育", notes = "人才详情-个人教育信息", response = Response.class)
	@RequestMapping(value = "/getPersonnelEdu", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<PersonnelEducation> getPersonnelEdu(@RequestParam Long personnelId) {
		try {
			return personnelService.getPersonnelEdu(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<PersonnelEducation>(Response.ERROR,"查看个人教育信息失败");
		}
	}
	@ApiOperation(value = "查看家庭信息", notes = "人才详情-家庭信息", response = Response.class)
	@RequestMapping(value = "/getFamilyMember", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<List<FamilyMember>> getFamilyMember(@RequestParam Long personnelId) {
		try {
 			return familyMemberService.getFamilyMember(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<FamilyMember>>(Response.ERROR,"查看家庭信息失败");
		}
	}
	@ApiOperation(value = "查看教育经历", notes = "人才详情-教育经历", response = Response.class)
	@RequestMapping(value = "/getEducational", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<List<Educational>> getEducational(@RequestParam Long personnelId) {
		try {
			return educationalService.getEducational(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Educational>>(Response.ERROR,"查看教育经历失败");
		}
	}
	@ApiOperation(value = "查看工作经历", notes = "人才详情-工作经历", response = Response.class)
	@RequestMapping(value = "/getWorkExperience", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Map<String,Object>> getWorkExperience(@RequestParam Long personnelId) {
		try {
			return workExperienceService.getWorkExperience(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Map<String,Object>>(Response.ERROR,"查看工作经历失败");
		}
	}
	@ApiOperation(value = "查看申請", notes = "人才详情-数据库表申請信息", response = Response.class)
	@RequestMapping(value = "/getApply", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Apply> getApply(@RequestParam String personnelId) {
		try {
			return applyService.getApply(personnelId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Apply>(Response.ERROR,"查看申请计划信息失败");
		}
	}
	@ApiOperation(value = "查看在线增员计划申请预览信息", notes = "在线增员-提交申请预览", response = Response.class)
	@RequestMapping(value = "/getScanApply", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<ApplyScan> getScanApply(@ApiParam(name = "personnelId", value = "人才Id") @RequestParam String personnelId) {
		Account account = (Account) redisUtil.getUserInfo();
		String accountId = redisUtil.getUserId().toString();
		Response<ApplyScan> resp = applyService.getScanApply(Long.valueOf(personnelId));
		Response<AgentBranchInfomation> agentBranchInfo = agentBranchInformationService.getAgentBranchInfoById(accountId);
		String branchName = "";//所属分公司城市
		String branchCity = "";//所属城市
		if(agentBranchInfo.getData()!=null){
			branchName = agentBranchInfo.getData().getBranchName();
			branchCity = agentBranchInfo.getData().getBranchCity();
		}
		//查找引荐人
		Response<Account> accountTemp = accountService.findById(Long.valueOf(accountId));
		Long referreeNo = 0l;
		String referreeName = "";
		if(accountTemp.getData()!=null){
			//获取引荐人id
		    referreeNo = accountTemp.getData().getSuperior();
		    if(referreeNo!=null){
				//根据引荐人id查询姓名
				referreeName = accountService.findById(referreeNo).getData().getAccountName();
		    }
		}
		String StrReferreeNo = String.valueOf(referreeNo);
		if(resp.getData()!=null){
			resp.getData().setReferrerNo(StrReferreeNo);
			resp.getData().setReferrerName(referreeName);
			resp.getData().setBranchName(branchName);
			resp.getData().setBranchCity(branchCity);
		}
		return resp;
	}
	@ApiOperation(value = "证件编号查个人信息", notes = "人才详情-根据证件编号查找个人信息", response = Response.class)
	@RequestMapping(value = "/getPersonnelByIdentityCode", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Personnel> getPersonnelByIdentityCode(@RequestParam String identityCode) {
		try {
			return personnelService.getPersonnelByIdentityCode(identityCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Personnel>(Response.ERROR,"根据证件编号查找个人信息失败");
		}
	}
	@ApiOperation(value = "代理人人才库", notes = "代理人人才库查询", response = Response.class)
	@RequestMapping(value = "/getAgentPersonnel", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<List<Personnel>> getAgentPersonnel(@RequestParam String accountId) {
		try {
			return personnelService.getAgentPersonnel(accountId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Personnel>>(Response.ERROR,"代理人人才库查询失败");
		}
	}
	@ApiOperation(value = "准增员人才库", notes = "准增员人才库查询", response = Response.class)
	@RequestMapping(value = "/getMustIncrease", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Page<PersonnelTagVo>> getMustIncrease(@RequestParam String accountId,@RequestParam(value="keyWord",required=false) String keyWord,
			@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		
		try {
			Page<PersonnelTagVo> page = new Page<PersonnelTagVo>(pageNo, pageSize);

			page.setRecords(personnelService.getMustIncrease(page,accountId,keyWord));
			Response<Page<PersonnelTagVo>> resp = new Response<Page<PersonnelTagVo>>(Response.SUCCESS, "准增员人才库查询成功");
			
			resp.setCode("1");
			resp.setData(page);
			
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Page<PersonnelTagVo>>(Response.ERROR,"准增员人才库查询失败");
		}
	}
	
	
	
	@ApiOperation(value = "人才库编辑", notes = "准增员人才库查询", response = Response.class)
	@RequestMapping(value = "/getMustIncreaseedit", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<PersonnelTagVo> getMustIncreaseedit(@RequestParam String accountId,
															  @RequestParam String personnelId
			) {
		
		try {
			
			List<PersonnelTagVo> personnelTagVolist =personnelService.getMustIncreaseedit(accountId,personnelId);
			Response<PersonnelTagVo> resp = new Response<PersonnelTagVo>(Response.SUCCESS, "准增员人才库查询成功");		
			resp.setCode("1");
			resp.setData(personnelTagVolist.get(0));
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<PersonnelTagVo>(Response.ERROR,"准增员人才库查询失败");
		}
	}
	
	@ApiOperation(value = "增员中人才库", notes = "增员中人才库查询", response = Response.class)
	@RequestMapping(value = "/getIncreaseing", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<List<Personnel>> getIncreaseing(@RequestParam String accountId) {
		try {
			return personnelService.getIncreaseing(accountId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Personnel>>(Response.ERROR,"增员中人才库查询失败");
		}
	}
	@ApiOperation(value = "人才库-查看", notes = "人才库查看页面信息查询", response = Response.class)
	@RequestMapping(value = "/getPersonnelPool", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<PersonnelPool> getPersonnelPool(@ApiParam(name = "personnelId", value = "人才Id")@RequestParam String personnelId) {
		try {
			Account account = (Account) redisUtil.getUserInfo();// 获取用户对象
			Long accountId = account.getId();
			Response<Account> accountTemp = accountService.findById(accountId);
			String referreeName = "";
			if(accountTemp.getData()!=null){
				//获取引荐人id
				Long referreeNo = accountTemp.getData().getSuperior();
				if(referreeNo!=null){
					//根据引荐人id查询姓名
					referreeName = accountService.findById(referreeNo).getData().getAccountName();
				}
			}
			//查找人才库信息
			Response<PersonnelPool> resp = personnelService.getPersonnelPool(personnelId);
			resp.getData().setReferrerName(referreeName);
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<PersonnelPool>(Response.ERROR,"人才库查看页面信息查询失败");
		}
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "上传文件", notes = "授权处理-上传文件", response = Response.class)
	@RequestMapping(value = "/uploadFile", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<Boolean> uploadFile(@RequestParam(value = "file", required = true)File file,HttpServletRequest request) throws IOException{
		try {
			return personnelService.uploadFile(file,request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Boolean>(Response.ERROR,"上传文件失败");
		}
    }
	@ApiOperation(value = "录入面试试题结果", notes = "面试试题结果录入", response = Response.class)
	@RequestMapping(value = "/insertPersonnelPaperInfo", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Integer> insertPersonnelPaperInfo(@RequestBody PersonnelPaperVo personnelPaperVo) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			return personnelPaperInfoService.insertPersonnelPaperInfoByList(personnelPaperVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"面试结果录入失败");
		}
	}
	
	@ApiOperation(value = "代理人信息录入完成更新", notes = "代理人状态更新", response = Response.class)
	@RequestMapping(value = "/updatePersonnel", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Integer> updatePersonnel(@ApiParam(name = "personnelStatus", value = "代理人状态")@RequestParam(required=false) String personnelStatus,
													  @ApiParam(name = "personnelProcess", value = "代理人所处节点")@RequestParam(required=false) String personnelProcess,
													  @ApiParam(name = "personnelId", value = "代理人id")@RequestParam(required=true) String personnelId,
													  @ApiParam(name = "flowActionId", value = "流程执行id")@RequestParam(required=false) String flowActionId,
													  @ApiParam(name = "orgId", value = "分公司id")@RequestParam(required=false) String orgId,
													  @ApiParam(name = "processingStatus", value = "流程状态")@RequestParam(required=false) String processingStatus,
													  @ApiParam(name = "processingDesc", value = "面试意见")@RequestParam(required=false) String processingDesc,
													  @ApiParam(name = "talentPlan", value = "人才计划")@RequestParam(required=false) String talentPlan
													  ) {
		try {
			Personnel personnel1 = personnelMapper.selectById(personnelId);
			System.out.println("---------------"+personnel1.getCreateUser()+"------------------------------");
			Account account = (Account) redisUtil.get(Constant.LOGIN_ACCOUNT+personnel1.getCreateUser());// 获取用户对象
			System.out.println("---------------"+account+"------------------------------");
			FlowAction flowAction = flowActionService.getFlowActionByOrgIdAndStep(personnel1.getOrgId(), Integer.valueOf(personnelStatus));
			//更新/插入流程信息
			InterviewAction interviewAction= new InterviewAction();
			if(flowAction.getProcessingRole() != null){
				if(flowAction.getProcessingRole()==1){
					interviewAction.setProcessingPerson(account.getBelongSuperior());
					//添加消息
				}else if(flowAction.getProcessingRole()==2){
					interviewAction.setProcessingPerson(account.getBelongDirector());
				}else{
					interviewAction.setProcessingPerson(Long.valueOf(10000678));
				}
			}
			interviewAction.setPersonnelId(Long.valueOf(personnelId));
			interviewAction.setFlowActionId(flowAction.getId());
			interviewAction.setProcessingName(account.getAccountName());
			interviewAction.setProcessingStatus(processingStatus);
			interviewAction.setProcessingDesc(processingDesc);
			interviewAction.setStartTime(new Date());
			interviewAction.setCreateTime(new Date());
			interviewAction.setCreateUser(account.getId());
			interviewAction.setFlowItemId(flowAction.getFlowItemId());
			
			Personnel personnel = new Personnel();
			personnel.setId(Long.valueOf(personnelId));
			personnel.setPersonnelStatus(personnelStatus);
			personnel.setName(personnel1.getName());
			personnel.setCreateUser(personnel1.getCreateUser());
			if(processingStatus != null && "2".equals(processingStatus)){
				personnel.setPersonnelStatus(CommenConstant.ER_Flow_NO_PASS);
				personnelMapper.updatePersonnelStatusByPersonnelId(personnel);
				return new Response<Integer>(Response.SUCCESS,"面试结束");
			}
			//修改人才计划
			if(talentPlan != null && !"".equals(talentPlan)){
				Apply apply = new Apply();
				apply.setPersonnelId(Long.valueOf(personnelId));
				apply.setTalentPlan(talentPlan);
				applyMapper.updateByPersonnelId(apply);
			}
			int result = personnelService.updatePersonnel(personnel,interviewAction,orgId,flowAction.getStep());
			Response<Integer> response = new Response<Integer>(Response.SUCCESS,"面试结果录入成功");
			response.setData(result);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"面试结果录入失败");
		}
	}
	
}
