package com.fulan.api.plan.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.plan.domain.PlanAuthority;

@FeignClient(name = "plan")
public interface ManagePlanAuthorityService {

	@GetMapping(value = "/manage/planAuthority/selectByCourseId")
	List<PlanAuthority> selectAuthorityByCourseId(@RequestParam(value="courseId")String courseId);
}
