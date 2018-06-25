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
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.application.service.AttendanceRulesService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 考勤规则前端控制器
 * </p>
 *
 * @author gaoyufei
 * @since 2018-04-28
 */
@Api(tags = "AttendanceRulesInterviewApi", description = "考勤规则接口")
@RestController
@RequestMapping("/attendance")
public class AttendanceRulesController {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceRulesController.class);
	
	@Autowired
	private AttendanceRulesService attendanceRulesService;
	@ApiOperation(value = "考勤规则图", notes = "考勤规则图", response = Response.class)
	@RequestMapping(value="/selectAttendanceRulesByParams",method=RequestMethod.GET)
	public @ResponseBody PageInfo<AttendanceRules> selectAttendanceRulesByParams(Integer month, Integer rulesType, String keyWord,int pageNo,int pageSize){
		Page<CourseCMSVo> page = new Page<CourseCMSVo>(pageNo, pageSize);
		return attendanceRulesService.selectAttendanceRulesByParams(page,month,rulesType,keyWord,pageNo,pageSize);
	}
	
}
