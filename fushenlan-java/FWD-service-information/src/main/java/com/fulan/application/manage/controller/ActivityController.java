package com.fulan.application.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.vo.ActivityVo;
import com.fulan.application.service.ActivityService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController
@RequestMapping("/manage/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	 /**
	 * 查咨活动列表
	 */
	@RequestMapping(value="/listactivity",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<ActivityVo> listactivity(
            @RequestParam(value="title",required=false) String title,
            @RequestParam(value="type",required=false) String type,
            @RequestParam(name="status",required = false) String status,
			 @RequestParam(value="actStartDate",required=false) String actStartDate,
    		 @RequestParam(value="actEndDate",required=false) String actEndDate,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<ActivityVo> page = new Page<ActivityVo>(pageNo, pageSize);
		PageInfo<ActivityVo> pageInfo = activityService.listactivity(page, title,type,status,actStartDate,actEndDate, pageNo, pageSize);
		return pageInfo;
	}
	
	
	 /**
	 * 活动发布，撤回
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.GET)
	@ResponseBody
	public Response<String> updateStatus(
			@RequestParam(name="id" ,required = false)String id,
			@RequestParam(name="status" ,required = false)String status){
		Response<String> response = activityService.updateStatus(id,status);
		return response;
	}
	
	
	/**
	 * 删除咨询
	 */
	@RequestMapping(value="/deleteActivity",method=RequestMethod.GET)
	@ResponseBody
	public Response<String> deleteActivity(
			@RequestParam(name="id" ,required = false)String id){
		Response<String> response = activityService.deleteActivity(id);
		return response;
	}
	
	/**
	 * 新增咨询
	 */
	@PostMapping(value="/insertActivity")
	@ResponseBody
	public Response<String> insertActivity(@RequestBody ActivityVo activityVo,
			@RequestParam(name="pathId" ,required = false)String pathId,
			@RequestParam(name="FilePathId" ,required = false)String FilePathId){
		Response<String> response = activityService.insertActivity(activityVo,pathId,FilePathId);
		return response;
	}
	
	/**
	 * 修改咨询
	 */
	@PostMapping(value="/updateActivity")
	@ResponseBody
	public Response<String> updateActivity(@RequestBody ActivityVo activityVo,
			@RequestParam(name="pathId" ,required = false)String pathId,
			@RequestParam(name="FilePathId" ,required = false)String FilePathId){
		Response<String> response = activityService.updateActivity(activityVo,pathId,FilePathId);
		return response;
	}
	
	 /**
	 * 通过id查询咨询对象
	 */
	@RequestMapping(value="/selectOneActivityById",method=RequestMethod.GET)
	@ResponseBody
	public ActivityVo selectOneActivityById(@RequestParam(value="id",required=false) String id){
		ActivityVo activityVo =activityService.selectOneActivityById(id);
		return activityVo;
	}
	
	
	 /**
		 * 查咨活动报名列表
		 */
		@RequestMapping(value="/activityApplyList",method=RequestMethod.GET)
		@ResponseBody
		public PageInfo<ApplyDetail> activityApplyList(
				@RequestParam(name="acounyName" ,required = false)String acounyName,
				@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	            @RequestParam(name="id" ,required = false)String id,
	            @RequestParam(name="signUpSet" ,required = false)String signUpSet){
			Page<ApplyDetail> page = new Page<ApplyDetail>(pageNo, pageSize);
			PageInfo<ApplyDetail> pageInfo = activityService.activityApplyList(page, acounyName, pageNo, pageSize,id,signUpSet);
			return pageInfo;
		}
	
	
}
