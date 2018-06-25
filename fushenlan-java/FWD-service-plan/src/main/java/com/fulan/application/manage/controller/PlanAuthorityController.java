package com.fulan.application.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.application.service.PlanAuthorityService;

@RestController
@RequestMapping("/manage/planAuthority")
public class PlanAuthorityController {

	@Autowired
	PlanAuthorityService planAuthorityService;

	@RequestMapping(value="/selectByCourseId",method=RequestMethod.GET)
	@ResponseBody
	public List<PlanAuthority> selectByCourseId(String courseId){
		return planAuthorityService.selectByCourseId(courseId);
	}
}
