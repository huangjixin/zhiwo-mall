package com.fulan.api.information.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.vo.ActivityVo;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 活动接口
 * </p>
 *
 */

@FeignClient(name = "information")
public interface ActivityService {
	
	
	/**
	 * 分页查活动
	 * 
	 */
	@GetMapping("/manage/activity/listactivity")
	PageInfo<ActivityVo> listactivity(
			@RequestParam(name="title" ,required = false)String title,
			@RequestParam(name="type",required = false) String type,
			@RequestParam(name="status",required = false) String status,
			 @RequestParam(value="actStartDate",required=false) String actStartDate,
    		 @RequestParam(value="actEndDate",required=false) String actEndDate,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
			);
	
	/**
	 * 活动发布，撤回
	 * 
	 */
	@GetMapping("/manage/activity/updateStatus")
	Response<String> updateStatus(
			@RequestParam(name="id" ,required = false)String id,
			@RequestParam(name="status" ,required = false)String status
			);
	
	/**
	 * 删除活动
	 * 
	 */
	@GetMapping("/manage/activity/deleteActivity")
	Response<String> deleteActivity(
			@RequestParam(name="id" ,required = false)String id
			);
	
	
	/**
	 * 新增活动
	 * 
	 */
	@PostMapping("/manage/activity/insertActivity")
	Response<String> insertActivity(@RequestBody ActivityVo activityVo,
					@RequestParam(name="pathId" ,required = false)String pathId,
					@RequestParam(name="FilePathId" ,required = false)String FilePathId
			);
	
	/**
	 * 修改活动
	 * 
	 */
	@PostMapping("/manage/activity/updateActivity")
	Response<String> updateActivity(@RequestBody ActivityVo activityVo,
			@RequestParam(name="pathId" ,required = false)String pathId,
			@RequestParam(name="FilePathId" ,required = false)String FilePathId);
	
	/**
	 * 通过id查询咨询对象
	 * 
	 */
	@GetMapping("/manage/activity/selectOneActivityById")
	ActivityVo selectOneActivityById(@RequestParam(name="id" ,required = false)String id);

	
	@GetMapping("/manage/activity/activityApplyList")
	PageInfo<ApplyDetail> activityApplyList(
			@RequestParam(name="acounyName" ,required = false)String acounyName,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name="id" ,required = false)String id,
            @RequestParam(name="signUpSet" ,required = false)String signUpSet);
	
	
}
