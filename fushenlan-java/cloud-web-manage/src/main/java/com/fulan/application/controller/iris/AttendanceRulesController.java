package com.fulan.application.controller.iris;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import com.fulan.api.calendar.service.AttendanceRuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.service.AttendanceResultService;
import com.fulan.api.calendar.service.AttendanceRuleService;
import com.fulan.api.calendar.service.ManageAttendanceRuleService;
import com.fulan.api.calendar.vo.AttendanceHistoryVo;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.AgentBasicInformation;
import com.fulan.api.security.domain.AgentBranchInfomation;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.AgentBasicInformationService;
import com.fulan.api.security.service.AgentBranchInformationService;
import com.fulan.api.security.vo.AccountAgentVo;
import com.fulan.api.system.domain.Level;
import com.fulan.api.system.service.LevelService;
import com.fulan.application.common.ReadExcel;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
import com.fulan.application.util.util.StringUtils;

/**
 * @Author: gaoyufei
 * @Date: 2018/4/10
 */
@Controller
@RequestMapping("/manage/attendance")
public class AttendanceRulesController {
	
	@Autowired
	private ManageAttendanceRuleService manageAttendanceRuleService;
	
	@Autowired
	private AttendanceRuleService attendanceRuleService;
	
	@Autowired
	private AttendanceResultService attendanceResultService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AgentBasicInformationService agentBasicInformationService;
	
	@Autowired
	private AgentBranchInformationService agentBranchInformationService;
	
	@Autowired
	private LevelService levelService;
	
	//工作日类型
	private static final int TRANSATION_TYPE_WORK = 2;
	//上传文件人员信息个数 
	private static final int AGENT_INFORMATION_COUNT = 3;
	
	/**
	 * 跳转新增或者详情页
	 */
	@RequestMapping("/toAddAttendanceRoles")
	public String toAddAttendanceRoles(Map<String,Object> map,
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="rulesType",required=false)String rulesType,
			@RequestParam(value="operatorType",required=false)String operatorType
			){
		//将代理人companyId带入页面
		String companyId = SessionContextUtils.getCurrentUser().getCompanyId();
		map.put("companyId", companyId);
		AttendanceRulesVo attendanceRulesVo = null;
		if(!StringUtils.isEmptry(id)){
			attendanceRulesVo = manageAttendanceRuleService.getAttendanceRulesVoById(Long.parseLong(id)).getData();
			map.put("attendanceRulesVo", attendanceRulesVo);
			//存入json数据方便遍历
			Object json = JSONArray.toJSON(attendanceRulesVo.getDailyTransactionList());     
			map.put("dailyTransactionList", json);
		}
		if(!StringUtils.isEmptry(rulesType)){
			//新增区分活动和基础考勤 1普通 2活动
			map.put("rulesType",rulesType);
		}else{
			//编辑或查看区分活动和基础考勤 1普通 2活动
			 rulesType = attendanceRulesVo.getAttendanceRules().getRulesType().toString();
			 map.put("rulesType",rulesType);
		}
		if(!StringUtils.isEmptry(operatorType)){
			//区分操作类型 0查看 1 修改
			map.put("operatorType",operatorType);
		}
		return "/iris/attendance/attendance_add";
	}
	
	/**
	 * 日历翻页暂存当页数据ajax
	 */
	@RequestMapping("/saveCurrentPageDailyTransaction")
	@ResponseBody
	public List<CalendarDailyTransaction> saveCurrentPageDailyTransaction( 
			Map<String,Object> map,
			AttendanceRulesVo attendanceRulesVo,
			@RequestParam(value="lastPageDailyTransactionsList",required=false)String lastPageDailyTransactionsList){
		//更新每日详情
		return updateLastPageDailyTransaction(attendanceRulesVo,lastPageDailyTransactionsList);
	}
	
	/**
	 * 新增或修改考勤规则
	 */
	@RequestMapping("/addOrUpdateAttendanceRules")
	@ResponseBody
	public Response<Integer> addOrUpdateAttendanceRules( 
			AttendanceRulesVo attendanceRulesVo,
			@RequestParam(value="lastPageDailyTransactionsList",required=false)String lastPageDailyTransactionsList){
		//更新每日详情
		List<CalendarDailyTransaction> finalDailyTransaction = updateLastPageDailyTransaction(attendanceRulesVo,lastPageDailyTransactionsList);
		attendanceRulesVo.setDailyTransactionList(finalDailyTransaction);
		//存储当前用户id和name
		Account currentUser = SessionContextUtils.getCurrentUser();
		attendanceRulesVo.setCurrentUserId(currentUser.getId());
		attendanceRulesVo.setCurrentUserName(currentUser.getAccountName());
		return manageAttendanceRuleService.addOrUpdateAttendanceRules(attendanceRulesVo);
	}
	
	/**
	 * 删除考勤规则
	 */
	@RequestMapping("/deleteAttendanceRules")
	@ResponseBody
	public Response<Integer> deleteAttendanceRules(
			@RequestParam(value="id",required=true)String id
			){
		long  attendanceRulesId = 1L;
		if(!StringUtils.isEmptry(id)){
			attendanceRulesId = Long.parseLong(id);
		}
		return manageAttendanceRuleService.deleteAttendanceRules(attendanceRulesId);
	}
			
	
	/**
	 * 用当前页详情更新往页数据方法
	 */
	public List<CalendarDailyTransaction> updateLastPageDailyTransaction(AttendanceRulesVo attendanceRulesVo,String lastPageDailyTransactionsList){
		List<CalendarDailyTransaction> dailyTransactionList = new ArrayList<CalendarDailyTransaction>();
		//得到本页的每日详情
		List<CalendarDailyTransaction> currentPagedailyTransactions = attendanceRulesVo.getDailyTransactionList();
		//新建时第一页若为空白则不添加本月数据,防止首月跳转空白月
		boolean isClicked = false;
		for(CalendarDailyTransaction cdt:currentPagedailyTransactions){
			if(cdt.getTransationType()==TRANSATION_TYPE_WORK){
				//有工作日代表点击
				isClicked = true;
			}
		}
		if(isClicked){
			dailyTransactionList.addAll(currentPagedailyTransactions);
		}
		if(!StringUtils.isEmptry(lastPageDailyTransactionsList)){
			//得到之前页的每日详情
			List<CalendarDailyTransaction> lastPageDailyTransactions = JSONArray.parseArray(lastPageDailyTransactionsList, CalendarDailyTransaction.class);
			//用本页数据更新重合数据
			List<CalendarDailyTransaction> toBeDelete = new ArrayList<CalendarDailyTransaction>();
			for(CalendarDailyTransaction cdt:currentPagedailyTransactions){
				for(CalendarDailyTransaction cdts:lastPageDailyTransactions){
					//判断日期是否相等使用gettime方法
					if(cdt.getDay()!=null&&cdts.getDay()!=null&&cdt.getDay().getTime()==cdts.getDay().getTime()){
						toBeDelete.add(cdts);
					}
				}
			}
			//TODO增加判断首个月数据被清空后删除的判断
			lastPageDailyTransactions.removeAll(toBeDelete);
			lastPageDailyTransactions.addAll(currentPagedailyTransactions);
			dailyTransactionList.clear();
			dailyTransactionList.addAll(lastPageDailyTransactions);
		}
		return dailyTransactionList;
	}
	
	/**
	 * 解析人员附件并返回agentId列表
	 */
	@RequestMapping("/resolveAttanchment")
	@ResponseBody
	public Response<List<AttendanceObjects>> resolveAttanchment(
			@RequestParam("url")String url,
			HttpServletRequest request){
		//根据代理人姓名返回agentId
		Response<List<AttendanceObjects>> response= new Response<List<AttendanceObjects>>();
		try {
			 MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		        //获取上传文件   name值 必须为"fileName"：
		        MultipartFile file = mRequest.getFile("fileName");
		        String name=file.getOriginalFilename();
		        if(!name.substring(name.indexOf(".")+1, name.length()).equals("xls")&&!name.substring(name.indexOf(".")+1, name.length()).equals("xlsx")){
		        	response.setCode(Response.ERROR);
		        	response.setMsg("请选择xls或xlsx格式文件");
		        	return response;
		        }
		        //从服务器读取文件流
		        SFTPUtil sftp = new SFTPUtil(CommenConstant.ftpuploadusername, CommenConstant.ftpuploadpassword, CommenConstant.ftpuploadhost, CommenConstant.ftpuploadport);
		        sftp.login();
		        InputStream inputStream = sftp.get("/home/apps/upload/"+url);
		        //将文件流转换为list 代理人信息格式为(agentCode companyId orgId)
		        ArrayList<String> agentDetailList = ReadExcel.excelIn(inputStream, 2);
		        ArrayList<AttendanceObjects> agentList = new  ArrayList<AttendanceObjects>();
				//将代理人信息转换为AttendanceObjects对象
				if(agentDetailList!=null&&agentDetailList.size()!=0){
					for(int i = 0;i < (agentDetailList.size()/AGENT_INFORMATION_COUNT);i++){
						//根据提供信息查询该人的id和orgId
						AttendanceObjects ao = new AttendanceObjects();
						ao.setAgentCode(agentDetailList.get(i*AGENT_INFORMATION_COUNT));
						ao.setCompanyId(agentDetailList.get(i*AGENT_INFORMATION_COUNT+1));
						ao.setOrgId(agentDetailList.get(i*AGENT_INFORMATION_COUNT+2));
						agentList.add(ao);
					}
				}
				//流获取完毕后再登出
				sftp.logout();
		        response.setData(agentList);
		} catch (Exception e) {
			response.setCode(Response.ERROR);
			response.setMsg("导入失败，请重试");
		}
		return response;
	}
	
	/**
	 * 考勤规则列表
	 * 
	 */
	@RequestMapping(value="/selectAttendanceRulesByParams",method=RequestMethod.GET)
	public String publicCourseListByPage(Model model,
			@RequestParam(value="month",required=false) String month,//月份
			@RequestParam(value="rulesType",required=false) String rulesType,//考勤规则类型
			@RequestParam(value="keyWord",required=false) String keyWord,//关键字
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="4") int pageSize){
		Integer checkMonth=initMonth(month);
		model.addAttribute("month", checkMonth);
		Map<String,PageInfo<AttendanceRules>> resuleMap=new HashMap<String,PageInfo<AttendanceRules>>();
		PageInfo<AttendanceRules> baseAttendanceRules=null;
		PageInfo<AttendanceRules> activityAttendanceRules=null;
		if(StringUtils.isEmptry(rulesType)){
			//考勤规则类型为空，查询基础和活动
			baseAttendanceRules = accessSelectAttendanceRules(checkMonth, 1, keyWord,pageNo, pageSize);
			activityAttendanceRules = accessSelectAttendanceRules(checkMonth, 2, keyWord,pageNo, pageSize);
			
    	}else if("1".equals(rulesType)){
    		//查基础考勤
    		baseAttendanceRules =accessSelectAttendanceRules(checkMonth, 1, keyWord,pageNo, pageSize);
    	}else if("2".equals(rulesType)){
    		//查活动考勤
    		activityAttendanceRules =accessSelectAttendanceRules(checkMonth, 2, keyWord,pageNo, pageSize);
    	}
		resuleMap.put("baseAttendanceRules", baseAttendanceRules);
		resuleMap.put("activityAttendanceRules", activityAttendanceRules);
		model.addAttribute("resuleMap", resuleMap);
		return "/iris/attendance/attendance_list";
	}
	
	public PageInfo<AttendanceRules> accessSelectAttendanceRules(Integer checkMonth,Integer rulesType,String keyWord,int pageNo,int pageSize){
		return attendanceRuleService.selectAttendanceRulesByParams(checkMonth, rulesType, keyWord,pageNo, pageSize);
	}
	/**
	 * 基础考勤规则列表
	 * 
	 */
	@RequestMapping(value="/selectBaseAttendanceRulesByParams",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<AttendanceRules> selectBaseAttendanceRulesByParams(
			@RequestParam(value="month",required=false) String month,//月份
			@RequestParam(value="rulesType",required=false) String rulesType,//考勤规则类型
			@RequestParam(value="keyWord",required=false) String keyWord,//关键字
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="4") int pageSize){
		Integer checkMonth=initMonth(month);
		PageInfo<AttendanceRules> PageInfoList=attendanceRuleService.selectAttendanceRulesByParams(checkMonth, 1, keyWord,pageNo, pageSize);
		return PageInfoList;
	}
	
	/**
	 * 活动考勤规则列表
	 * 
	 */
	@RequestMapping(value="/selectActivityAttendanceRulesByParams",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<AttendanceRules> selectActivityAttendanceRulesByParams(
			@RequestParam(value="month",required=false) String month,//月份
			@RequestParam(value="rulesType",required=false) String rulesType,//考勤规则类型
			@RequestParam(value="keyWord",required=false) String keyWord,//关键字
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="4") int pageSize){
		Integer checkMonth=initMonth(month);
		PageInfo<AttendanceRules> PageInfoList=attendanceRuleService.selectAttendanceRulesByParams(checkMonth, 2, keyWord,pageNo, pageSize);
		return PageInfoList;
	}
	
	public Integer initMonth(String month){
		Integer checkMonth;
		//月份为空时选择当前月份
		if(StringUtils.isEmptry(month)){
			Calendar calendar=Calendar.getInstance();
			checkMonth=calendar.get(Calendar.MONTH)+1;
    	}else{
    		checkMonth=Integer.valueOf(month);
    	}
		return checkMonth;
	}
	
	/**
	 * 考勤结果列表
	 * selectAttendanceResultByParams
	 */
	@RequestMapping(value="/selectAttendanceResultByParams",method=RequestMethod.GET)
	public String selectAttendanceResultByParams(Model model,
			@RequestParam(value="keyWord",required=false) String keyWord,//工号或姓名
			@RequestParam(value="startDate",required=false) String startDate,//考勤开始日期
			@RequestParam(value="endDate",required=false) String endDate,//考勤结束日期
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		//回显检索条件
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		//分页查询考勤结果列表
		PageInfo<AttendanceHistory> pageInfo = attendanceResultService.selectAttendanceResultByParams(startDate,endDate, keyWord, pageNo, pageSize);
		model.addAttribute("page", setPageInfo(pageInfo));
		return "/iris/attendance/attendanceResult_list";
	}
	
	/**
	 *考勤结果列表ajax请求
	 * selectAttendanceResultByPageAjax
	 */
	@RequestMapping(value="/selectAttendanceResultByPageAjax",method=RequestMethod.GET)
	@ResponseBody
	public Response<PageInfo<AttendanceHistoryVo>> selectAttendanceResultByPageAjax(
			@RequestParam(value="keyWord",required=false) String keyWord,//工号或姓名
			@RequestParam(value="startDate",required=false) String startDate,//考勤开始日期
			@RequestParam(value="endDate",required=false) String endDate,//考勤结束日期
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		PageInfo<AttendanceHistory> pageInfo = attendanceResultService.selectAttendanceResultByParams(startDate,endDate, keyWord, pageNo, pageSize);
		Response<PageInfo<AttendanceHistoryVo>> res = new Response<>(Response.SUCCESS, null);	
		res.setData(setPageInfo(pageInfo));
		return res;
	}
	
	public PageInfo<AttendanceHistoryVo> setPageInfo(PageInfo<AttendanceHistory> pageInfo){
		//显示页面数据
		PageInfo<AttendanceHistoryVo> page=new PageInfo<AttendanceHistoryVo>();
		//代理人id 账户id 职级id 职级名
		List<AccountAgentVo> allAccountAgent = accountService.selectAllAccountAgent();
		//查询考勤人
		//List<AgentBasicInformation> allAgentBasicInformation = agentBasicInformationService.selectAllAgentBasicInformation();
		//查询分公司
		List<AgentBranchInfomation> allAgentBranchInformation = agentBranchInformationService.selectAllAgentBranchInformation();
		//查询所有的职级
		Response<List<Level>> levelLists = levelService.getLevelList();
		List<AttendanceHistory> records = pageInfo.getRecords();
		List<Level> levelList=levelLists.getData();
		//遍历为allAccountAgent集合中的职级名赋值
			for (AccountAgentVo accountAgentVo : allAccountAgent) {
				for (Level level : levelList) {
				if(level.getId().toString().equals(accountAgentVo.getPostType())){
					accountAgentVo.setLevelName(level.getLevelName());
				}
			}
		}
		List<AttendanceHistoryVo> attendanceHistoryList=new ArrayList<AttendanceHistoryVo>();
		//遍历为attendanceHistoryList设置值，页面显示
		for (AttendanceHistory attendanceHistory : records) {
			AttendanceHistoryVo attendanceHistoryVo=new AttendanceHistoryVo();
			if(!StringUtil.isEmpty(attendanceHistory.getAgentCode())){
//				for (AgentBasicInformation  agentBasicInformation: allAgentBasicInformation) {
//					if(attendanceHistory.getAgentCode().equals(agentBasicInformation.getAgentId())){
//						attendanceHistoryVo.setName(agentBasicInformation.getAgentName());
//					}
//				}
				for (AgentBranchInfomation agentBranchInfomation : allAgentBranchInformation) {
					if(attendanceHistory.getAgentCode().equals(agentBranchInfomation.getAgentId())){
						attendanceHistoryVo.setCompanyName(agentBranchInfomation.getBranchName());
					}
				}
				for (AccountAgentVo accountAgentVo : allAccountAgent) {
					if(attendanceHistory.getAgentCode().equals(accountAgentVo.getAgentId())){
						attendanceHistoryVo.setLevelName(accountAgentVo.getLevelName());
					}
				}
			}
			attendanceHistoryVo.setId(attendanceHistory.getId());
			attendanceHistoryVo.setAgentCode(attendanceHistory.getAgentCode());
			attendanceHistoryVo.setName(attendanceHistory.getAgentName());
			attendanceHistoryVo.setCheckDate(attendanceHistory.getCheckDate());
			attendanceHistoryVo.setCheckTime(attendanceHistory.getCheckTime());
			attendanceHistoryList.add(attendanceHistoryVo);
		}
		//设置页面信息
		page.setRecords(attendanceHistoryList);
		page.setPageNo(pageInfo.getPageNo());
		page.setPageSize(pageInfo.getPageSize());
		page.setPageTotal(pageInfo.getPageTotal());
		return page;
	}
}