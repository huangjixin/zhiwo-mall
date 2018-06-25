package com.fulan.api.calendar.vo;

import java.io.Serializable;
import java.util.List;

import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;

import lombok.Data;

/**
 * <p>
 * 考勤规则制定页Vo
 * </p>
 *
 */

@Data
public class AttendanceRulesVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//考勤规则
	private AttendanceRules attendanceRules;
	
	//日历
	private CalendarBook calendarBook;
	
	//日历每日详情
	private List<CalendarDailyTransaction> dailyTransactionList;
	
	//考勤对象
	private List<AttendanceObjects> attendanceObjectsList;
	
	//登录用户id
	private long currentUserId;
	
	//登录用户姓名
	private String currentUserName;
}
