package com.fulan.api.calendar.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "calendar")
public interface AttendanceResultService {
	
	@GetMapping("/attendance/selectAttendanceResultByParams")
	PageInfo<AttendanceHistory> selectAttendanceResultByParams(@RequestParam(value="startDate",required=false) String startDate,
			@RequestParam(value="endDate",required=false) String endDate,
			@RequestParam(value="keyWord",required=false) String keyWord,
			@RequestParam(value="pageNo",required=false) int pageNo,
            @RequestParam(value="pageSize",required=false) int pageSize);

}
