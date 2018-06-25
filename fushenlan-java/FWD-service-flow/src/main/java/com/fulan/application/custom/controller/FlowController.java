package com.fulan.application.custom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.domain.InterviewAction;
import com.fulan.api.flow.vo.FiowDetailVo;
import com.fulan.api.flow.vo.FlowVO;
import com.fulan.api.flow.vo.InterviewerDetialVo;
import com.fulan.api.flow.vo.PersonnelAddVo;
import com.fulan.api.paper.service.PaperManageService;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.personnel.service.PersonnelService;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.TargetService;
import com.fulan.api.security.vo.AccountVO;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.api.system.service.OrganizationService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.FlowActionMapper;
import com.fulan.application.mapper.FlowMapper;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.FlowService;
import com.fulan.application.service.InterviewActionService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.cat.constant.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 面试流程 前端控制器
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Api(tags = "FlowInterviewApi", description = "面试流程接口")
@RestController
@RequestMapping("/customer/interviewFlow")
public class FlowController {

	private static final Logger logger = LoggerFactory.getLogger(FlowController.class);

	@Autowired
	private FlowService flowService;

	@Autowired
	private FlowMapper flowMapper;
	
	@Autowired
	private FlowActionMapper flowActionMapper;

	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private PaperManageService paperManageService;

	@Autowired
	private InterviewActionService interviewActionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private TargetService targetService;

	@Autowired
	private ManageOrganizationService manageOrganizationService;
	
	@Autowired
	RedisUtil redisUtil;
	
	
	@ApiOperation(value = "代理人基本信息", notes = "代理人基本信息", response = Response.class)
	@RequestMapping(value = "/findbyaccountId",  method = RequestMethod.POST)
	public Response<AccountVO>  findAccountbyaccountId(
			  @ApiParam(name = "accountId", value = "代理人id") @RequestParam(required=false) Long  accountId) {
		try {
			
			
			Account	account = (Account)redisUtil.getUserInfo();//获取当前代理人
			Response<Organization>  res = new Response<Organization>(Response.SUCCESS, "查询组织成功");;
			if(account != null && account.getCompanyId() != null ){
				 res=  organizationService.findById(account.getCompanyId());
			}
			Response<AccountVO> resp=new Response<AccountVO>(Response.SUCCESS, "查询代理人成功");
			AccountVO  accountVO = new AccountVO();
			BeanUtils.copyProperties(account, accountVO);
			List<Long> accountIds = targetService.selectallaccountIds(account.getOrgId());
			int teamSize = 0;
			if(accountIds.size()>0){
				 teamSize =	personnelService.selectteamscalecountbyaccountId(accountIds);
			}
			accountVO.setTeamSize((long)teamSize);
			accountVO.setCompanyName(res.getData().getCnName());
			resp.setCode("1");
			resp.setData(accountVO);		
			return resp;	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<AccountVO>(Response.ERROR,"查询代理人失败");
		}
	}
	
	
	
	
	
	
	

	@ApiOperation(value = "面试流程图", notes = "面试流程图", response = Response.class)
	@RequestMapping(value = "/flowNodebyParam", method = RequestMethod.GET)
	public Response<List<FlowVO>> flowNodebyParam(
			@ApiParam(name = "personnelId", value = "人才id") @RequestParam(required = true) String personnelId,
			@ApiParam(name = "orgId", value = "分公司id") @RequestParam(required = true) String orgId) {
		try {

			Response<List<FlowVO>> resp = new Response<List<FlowVO>>(Response.SUCCESS, "查询面试流程图成功");
			List<FlowVO> FlowVOlist = flowService.flowNodebyParam(personnelId, orgId);
			resp.setCode("1");
			resp.setData(FlowVOlist);
			return resp;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<List<FlowVO>>(Response.ERROR, "查询面试流程图失败");
		}
	}

	@ApiOperation(value = "面试信息", notes = "面试信息", response = Response.class)
	@RequestMapping(value = "/interviewmessage", method = RequestMethod.POST)
	public Response<Object> interviewmessage(
			@ApiParam(name = "personnelId", value = "人才id") @RequestParam(required = false) long personnelId,
			@ApiParam(name = "flowActionId", value = "流程执行ID") @RequestParam(required = false) long flowActionId,
			@ApiParam(name = "flowItemId", value = "面试基础项ID") @RequestParam(required = false) long flowItemId,
			@ApiParam(name = "accountId", value = "代理人ID") @RequestParam(required = false) long accountId,
			@ApiParam(name = "orgId", value = "组织ID") @RequestParam(required = false) String orgId,
			@ApiParam(name = "moudleName", value = "模块名(区别 1 查询试卷类型，还是个人信息)") @RequestParam(required = false) String moudleName) {
		try {

			if (moudleName.equals(CommenConstant.ER_Flow_first_time)||moudleName.equals(CommenConstant.ER_Flow_Selection)||moudleName.equals(CommenConstant.ER_Flow_Decision)) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("personnelId", personnelId);
				map.put("flowItemId", flowItemId);
				Response<List<PaperDetailVo>> re = paperManageService.getPaperVo(map);// 查询试卷信息

				InterviewAction interviewAction = new InterviewAction();
				interviewAction.setPersonnelId(personnelId);
				interviewAction.setFlowActionId(flowActionId);
				//interviewAction.setFlowItemId(flowItemId);
				EntityWrapper<InterviewAction> wrapper = new EntityWrapper<>(interviewAction);

				FiowDetailVo fiowDetailVo = new FiowDetailVo();

				InterviewerDetialVo interviewerDetialVo = new InterviewerDetialVo();
				InterviewAction interviewActiondetail = interviewActionService.selectOne(wrapper);
				interviewerDetialVo.setProcessingName(interviewActiondetail.getProcessingName());// 面试官名字
				Account account = (Account) redisUtil.get(Constant.LOGIN_ACCOUNT+interviewActiondetail.getProcessingPerson());// 获取用户对象
				interviewerDetialVo.setPostType(account.getPostType());
				Organization organization = manageOrganizationService.getOrganizationById(orgId);
				if(organization != null){
					interviewerDetialVo.setOrgName(organization.getCnName());//机构名称
				}
				
				List<Long> accountIds = targetService.selectallaccountIds(account.getOrgId());
				int teamSize = 0;
				if(accountIds.size()>0){
				 teamSize =	personnelService.selectteamscalecountbyaccountId(accountIds);
				}
				interviewerDetialVo.setTeamSize((long)teamSize);
				interviewerDetialVo.setProcessingStatus(interviewActiondetail.getProcessingStatus());
				
				PersonnelManageInfoVo personnelManageInfoVo = personnelService.findById(personnelId + "");// 查询个人信息
				interviewerDetialVo.setProcessingDesc(personnelManageInfoVo.getPersonnel().getCheakOption());
				
				
				 fiowDetailVo.setInterviewerDetialVo(interviewerDetialVo);
				// 查询面试官面试信息
				fiowDetailVo.setPaperList(re.getData());

				Response<Object> resp = new Response<Object>(Response.SUCCESS, "查询面试详情成功");
				resp.setCode("1");
				resp.setData(fiowDetailVo);
				return resp;
			} else {
				PersonnelManageInfoVo personnelManageInfoVo = personnelService.findById(personnelId + "");// 查询个人信息
				Response<Object> resp = new Response<Object>(Response.SUCCESS, "查询面试详情成功");

				InterviewAction interviewAction = new InterviewAction();
				interviewAction.setPersonnelId(personnelId);
				interviewAction.setFlowActionId(flowActionId);
				interviewAction.setFlowItemId(flowItemId);
				EntityWrapper<InterviewAction> wrapper = new EntityWrapper<>(interviewAction);

				FiowDetailVo fiowDetailVo = new FiowDetailVo();
				

				InterviewerDetialVo interviewerDetialVo = new InterviewerDetialVo();		
				Account account = (Account) redisUtil.get(Constant.LOGIN_ACCOUNT+accountId);//  获取代理人信息
				interviewerDetialVo.setProcessingName(account.getAccountName());//  获取代理人名字
				interviewerDetialVo.setPostType(account.getPostType());
				Organization organization = manageOrganizationService.getOrganizationById(orgId);
				if(organization != null){
					interviewerDetialVo.setOrgName(organization.getCnName());//机构名称
				}
				List<Long> accountIds = targetService.selectallaccountIds(account.getOrgId());
				
				int teamSize = 0;
				if(accountIds.size()>0){
					 teamSize =	personnelService.selectteamscalecountbyaccountId(accountIds);
				}
				interviewerDetialVo.setTeamSize((long)teamSize);
				interviewerDetialVo.setProcessingDesc(personnelManageInfoVo.getPersonnel().getCheakOption());
				
				fiowDetailVo.setInterviewerDetialVo(interviewerDetialVo);
				fiowDetailVo.setPersonnelManageInfoVo(personnelManageInfoVo);

				resp.setCode("1");
				resp.setData(fiowDetailVo);
				return resp;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

	@ApiOperation(value = "我的增员", notes = "我的增员", response = Response.class)
	@RequestMapping(value = "/findPersonnelbyagentCode", method = RequestMethod.GET)
	public Response<Page<PersonnelAddVo>> findPersonnelbyagentCode(
			@ApiParam(name = "agentCode", value = "代理人code") @RequestParam(required = true) String agentCode,
			@ApiParam(name = "orgId", value = "分公司ID") @RequestParam(required = true) String orgId,
			@ApiParam(name = "keyWord", value = "关键词查询") @RequestParam(defaultValue = "") String keyWord,
			@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		try {
			Page<PersonnelAddVo> page = new Page<PersonnelAddVo>(pageNo, pageSize);

			page.setRecords(flowMapper.PersonnelSearchbyagentCode(page, orgId, agentCode, keyWord, pageNo, pageSize));

			Response<Page<PersonnelAddVo>> resp = new Response<Page<PersonnelAddVo>>(Response.SUCCESS, "我的增员");
			resp.setCode("1");
			resp.setData(page);
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Page<PersonnelAddVo>>(Response.ERROR, "查询我的增员失败");
		}
	}

	@ApiOperation(value = "我的面试", notes = "我的面试", response = Response.class)
	@RequestMapping(value = "/PersonnelpaperSearchbyParam", method = RequestMethod.GET)
	public Response<Page<PersonnelAddVo>> PersonnelpaperSearchbyParam(
			@ApiParam(name = "agentCode", value = "代理人code") @RequestParam(required = true) String agentCode,
			@ApiParam(name = "orgId", value = "分公司ID") @RequestParam(required = true) String orgId,
			@ApiParam(name = "keyWord", value = "关键词查询") @RequestParam(defaultValue = "") String keyWord,
			@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		try {
			Page<PersonnelAddVo> page = new Page<PersonnelAddVo>(pageNo, pageSize);

			page.setRecords(flowMapper.PersonnelpaperSearchbyParam(page, orgId, agentCode, keyWord, pageNo, pageSize));

			Response<Page<PersonnelAddVo>> resp = new Response<Page<PersonnelAddVo>>(Response.SUCCESS, "查询我的面试成功");
			resp.setCode("1");
			resp.setData(page);
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Page<PersonnelAddVo>>(Response.ERROR, "查询我的面试失败");
		}
	}
	
	
	@ApiOperation(value = "我的签约", notes = "我的签约", response = Response.class)
	@RequestMapping(value = "/PersonnelSignSearchbyParam", method = RequestMethod.GET)
	public Response<Page<PersonnelAddVo>> PersonnelSignSearchbyParam(
			@ApiParam(name = "agentCode", value = "代理人code") @RequestParam(required = true) String agentCode,
			@ApiParam(name = "orgId", value = "分公司ID") @RequestParam(required = true) String orgId,
			@ApiParam(name = "keyWord", value = "关键词查询") @RequestParam(defaultValue = "") String keyWord,
			@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		try {
			Page<PersonnelAddVo> page = new Page<PersonnelAddVo>(pageNo, pageSize);

			page.setRecords(flowMapper.PersonnelSignSearchbyParam(page, orgId, agentCode, keyWord, pageNo, pageSize));

			Response<Page<PersonnelAddVo>> resp = new Response<Page<PersonnelAddVo>>(Response.SUCCESS, "查询我的面试成功");
			resp.setCode("1");
			resp.setData(page);
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Page<PersonnelAddVo>>(Response.ERROR, "查询我的面试失败");
		}
	}
	
	
	
	@ApiOperation(value = "获取面试流程", notes = "获取面试流程", response = Response.class)
	@RequestMapping(value = "/getFlowActionById", method = RequestMethod.GET)
	public FlowAction getFlowActionById(@ApiParam(name = "id", value = "流程id") @RequestParam(required = true) long id){
		return flowActionMapper.selectById(id);
	}
	

	@ApiOperation(value = "获取面试流程", notes = "获取面试流程", response = Response.class)
	@RequestMapping(value = "/getFlowActionByOrgIdAndStep", method = RequestMethod.GET)
	public FlowAction getFlowActionByOrgIdAndStep(@ApiParam(name = "orgId", value = "分公司id") @RequestParam(required = true) String orgId,@ApiParam(name = "step", value = "步骤step") @RequestParam(required = true) int step){
	
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("orgId", orgId);
		searchMap.put("step", step);
		return flowActionMapper.getFlowActionByOrgIdAndStep(searchMap);
	}

}
