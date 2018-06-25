package com.fulan.api.calendar.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "calendar")
public interface AttendanceRuleService {
	
	@GetMapping("/attendance/selectAttendanceRulesByParams")
	PageInfo<AttendanceRules> selectAttendanceRulesByParams(@RequestParam(value="month",required=false) Integer month,
			@RequestParam(value="rulesType",required=false) Integer rulesType,
			@RequestParam(value="keyWord",required=false) String keyWord,
			@RequestParam(value="pageNo",required=false) int pageNo,
            @RequestParam(value="pageSize",required=false) int pageSize);

}
