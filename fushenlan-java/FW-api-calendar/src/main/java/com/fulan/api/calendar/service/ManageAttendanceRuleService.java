package com.fulan.api.calendar.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "calendar")
public interface ManageAttendanceRuleService {
	
	@RequestMapping(value = "/manage/attendanceRules/getAttendanceRulesVoById")
	Response<AttendanceRulesVo> getAttendanceRulesVoById(@RequestParam(value="attendanceRulesId",required=false)long attendanceRulesId);
	
	@RequestMapping(value = "/manage/attendanceRules/addOrUpdateAttendanceRules")
	Response<Integer> addOrUpdateAttendanceRules(@RequestBody AttendanceRulesVo attendanceRulesVo);
	
	@RequestMapping(value = "/manage/attendanceRules/deleteAttendanceRules")
	Response<Integer> deleteAttendanceRules(@RequestParam(value="attendanceRulesId",required=true)long attendanceRulesId);

	@RequestMapping(value ="/manage/attendanceRules/findAllDailyTransaction")
	Response<List<CalendarDailyTransaction>> findAllDailyTransaction(@RequestParam(value="rulesType",required=false)int rulesType);
}
