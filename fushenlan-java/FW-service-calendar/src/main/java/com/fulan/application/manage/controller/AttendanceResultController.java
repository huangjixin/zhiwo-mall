package com.fulan.application.manage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.application.service.AttendanceHistoryService;
import com.fulan.application.service.AttendanceRulesService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 考勤结果 前端控制器
 * </p>
 *
 * @author gaoyufei
 * @since 2018-04-28
 */
@Api(tags = "AttendanceResultInterviewApi", description = "考勤结果接口")
@RestController
@RequestMapping("/attendance")
public class AttendanceResultController {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceResultController.class);
	
	@Autowired
	private AttendanceHistoryService attendanceHistoryService;
	@ApiOperation(value = "考勤结果图", notes = "考勤结果图", response = Response.class)
	@RequestMapping(value="/selectAttendanceResultByParams",method=RequestMethod.GET)
	public @ResponseBody PageInfo<AttendanceHistory> selectAttendanceResultByParams(String startDate, String endDate, String keyWord,int pageNo,int pageSize){
		Page<AttendanceHistory> page = new Page<AttendanceHistory>(pageNo, pageSize);
		return attendanceHistoryService.selectAttendanceResultByParams(page,startDate,endDate,keyWord,pageNo,pageSize);
	}
	
}
