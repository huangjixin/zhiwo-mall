package com.fulan.application.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface AttendanceRulesService extends IService<AttendanceRules>{

	Response<AttendanceRulesVo> getAttendanceRulesById(long attendanceRulesId);
	
	Response<Integer> addOrUpdateAttendanceRulesVo(AttendanceRulesVo attendanceRulesVo);
	
	Response<Integer> deleteAttendanceRules(long attendanceRulesId);
	
	PageInfo<AttendanceRules> selectAttendanceRulesByParams(Page<CourseCMSVo> page,@Param("month") Integer month,@Param("rulesType") Integer rulesType,@Param("keyWord") String keyWord,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);


}
