package com.fulan.api.plan.service;


import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 班级计划
 * </p>
 *
 * @author chailaoer
 * @since 2018-01-22
 */
@FeignClient(name = "plan")
public interface ClassPlanService {
	
	@GetMapping("manage/classPlan/listPage")
	PageInfo<ClassPlanVo> listPage(
			@RequestParam(value="name",required=false)String name ,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="tagId",required=false)String tagId,
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
	        @RequestParam(value="pageSize",defaultValue="10") int pageSize);

	@PostMapping("manage/classPlan/updateOrDele")
	Response<String> updateOrDele(@RequestParam("ids") String ids,@RequestParam("state") String state);
	
	@GetMapping("/manage/classPlan/classPlanById")
    Response<ClassPlan> classPlanById(@RequestParam(name="id") Long id);
	
	@PostMapping("manage/classPlan/insertClassPlan")
	Response<String> insertClassPlan(@RequestBody ClassPlanFwVo classPlanFwVo,
			@RequestParam(name="userId")Long userId, @RequestParam(name="fileId",required=false)Long fileId);
	
	@PostMapping("manage/classPlan/findOnePlansFW")
	Map<String,Object> findOnePlansFW(@RequestParam("id") String id);
}
