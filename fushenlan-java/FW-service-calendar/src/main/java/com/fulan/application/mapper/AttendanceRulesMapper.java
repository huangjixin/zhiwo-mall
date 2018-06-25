package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.course.vo.CourseCMSVo;

@Mapper
public interface AttendanceRulesMapper extends BaseMapper<AttendanceRules>{
	
	List<AttendanceRules> selectAttendanceRulesByParams(Page<CourseCMSVo> page,@Param("month") Integer month,@Param("rulesType") Integer rulesType,@Param("keyWord") String keyWord,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);

	int selectAttendanceRulesCountByParams(@Param("month") Integer month,@Param("rulesType") Integer rulesType,@Param("keyWord") String keyWord);
}
