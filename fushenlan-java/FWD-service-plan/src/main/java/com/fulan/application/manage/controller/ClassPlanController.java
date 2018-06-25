package com.fulan.application.manage.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.application.service.ClassPlanService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
/**
 * 班级计划  控制器
 * @author kang
 *
 */
@RestController
@RequestMapping("/manage/classPlan")
public class ClassPlanController {
	
	@Autowired
	private ClassPlanService classPlanService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassPlanController.class);
	
	@GetMapping("/listPage")
	public PageInfo<ClassPlanVo> listPage(
			@RequestParam(value="name",required=false)String name ,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="tagId",required=false)String tagId,
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
	        @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<ClassPlanVo> page = new Page<ClassPlanVo>(pageNo, pageSize);
		PageInfo<ClassPlanVo> pageInfo = classPlanService.listByPageFM(page, name, code, tagId, pageNo, pageSize);
		return pageInfo;
	}
	
	@PostMapping("updateOrDele")
	public Response<String> updateOrDele(@RequestParam("ids") String ids,@RequestParam("state") String state){
		try {
			return classPlanService.updateOrDeleForManage(ids,state);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
	    }
			
	}
	
	@PostMapping("insertClassPlan")
	public Response<String> insertClassPlan(@RequestBody ClassPlanFwVo classPlanFwVo,
			@RequestParam("userId") Long userId, @RequestParam(name="fileId",required=false)Long fileId){
		try {
			ClassPlan classPlan = classPlanFwVo.getClassPlan();
			PlanCourseDto[] planCourseDtos = new PlanCourseDto[]{};
			/*PlanAuthorityDto[] planAuthorityDtos = new PlanAuthorityDto[]{};*/
			ExpiredAlarm[] expiredAlarms = new ExpiredAlarm[]{};
			PlanAuthority[] planAuthoritys = new PlanAuthority[]{};
			Long[] managerIds = new Long[]{};
			String isNot = "0";
			if(null != classPlanFwVo.getPlanCourseDto()){
				planCourseDtos = classPlanFwVo.getPlanCourseDto().toArray(planCourseDtos);
			}
			if(null != classPlanFwVo.getManagerIds()){
				managerIds = classPlanFwVo.getManagerIds();
			}
			if(null != classPlanFwVo.getExpiredAlarmList()){
				expiredAlarms = classPlanFwVo.getExpiredAlarmList().toArray(expiredAlarms);
			}
			if(StringUtils.isNotEmpty(classPlanFwVo.getIsNot())){
				isNot = classPlanFwVo.getIsNot();
			}
			if(null != classPlanFwVo.getPlanAuthorityList()){
				planAuthoritys = classPlanFwVo.getPlanAuthorityList().toArray(planAuthoritys);
			}
			return classPlanService.insertClassPlanFW(classPlan, planCourseDtos, managerIds, userId, expiredAlarms,isNot,planAuthoritys,fileId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
	    }
	}
	/*@PostMapping("updateClassPlanFW")
	public Response<String> updateClassPlanFW(@RequestBody ClassPlanFwVo classPlanFwVo,
			@RequestParam("userId") Long userId){
		try {
			ClassPlan classPlan = classPlanFwVo.getClassPlan();
			PlanCourseDto[] planCourseDtos = new PlanCourseDto[]{};
			PlanAuthorityDto[] planAuthorityDtos = new PlanAuthorityDto[]{};
			Long[] managerIds = new Long[]{};
			if(null != classPlanFwVo.getPlanCourseDto()){
				planCourseDtos = classPlanFwVo.getPlanCourseDto().toArray(planCourseDtos);
			}
			if(null != classPlanFwVo.getPlanAuthorityDto()){
				planAuthorityDtos= classPlanFwVo.getPlanAuthorityDto().toArray(planAuthorityDtos);
			}
			if(null != classPlanFwVo.getManagerIds()){
				managerIds = classPlanFwVo.getManagerIds();
			}
			if(null == classPlan.getId()){
				return classPlanService.insertClassPlanFW(classPlan, planCourseDtos, planAuthorityDtos, managerIds, userId);
			}else{
				return classPlanService.updateClassPlanFW(classPlan, planCourseDtos, planAuthorityDtos, managerIds, userId);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
		}
	}*/
	@PostMapping("findOnePlansFW")
	public Map<String,Object> findOnePlansFW(@RequestParam("id") String id){
		Map<String,Object> returnMap = classPlanService.findOnePlansFW(id);
		return returnMap;
	}
	@GetMapping("/classPlanById")
	public Response<ClassPlan> classPlanById(@RequestParam(name="id") Long id){
		Response<ClassPlan> res = new Response<ClassPlan>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		try {
			ClassPlan classPlan = classPlanService.findClassPlanDetailById(id);
			res.setData(classPlan);
			return res;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
		
	}
	
}
