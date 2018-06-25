package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.ExpiredAlarm;

@Mapper
public interface ExpiredAlarmMapper extends BaseMapper<ExpiredAlarm>{
	 
	int deleteCourseId(@Param("cId")String cId);

}
