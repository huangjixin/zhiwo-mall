package com.fulan.application.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.vo.CalendarBookDetailVo;
import com.fulan.api.calendar.vo.CalendarBookVo;

@Mapper
public interface CalendarBookMapper extends BaseMapper<CalendarBook>{

	int countCalenderByName(String calendarName);

	List<CalendarBookVo> findCalenderByName(Page<CalendarBookVo> page, String calendarName);

	CalendarBookDetailVo selectCalendarBookDetail(long calendarId);

	int findMaxMatainMonth();
}