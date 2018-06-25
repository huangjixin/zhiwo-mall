package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.application.util.page.PageInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AttendanceHistoryService {

	 void addAttendanceHistory(AttendanceHistory attendanceHistory) ;

	 List<Map<String,Object>> selectAllHistory(String calendarDate,String agentCode,String orgId,String companyId);

	 Integer selectCountByCalendarDate(String calendarDate,String agentCode,String orgId);
	 
	 PageInfo<AttendanceHistory> selectAttendanceResultByParams(Page<AttendanceHistory> page,String startDate,
	            String endDate,String keyWord,int pageNo,int pageSize);

}
