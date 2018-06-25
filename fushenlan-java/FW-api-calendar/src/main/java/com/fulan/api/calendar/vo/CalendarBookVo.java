package com.fulan.api.calendar.vo;

import java.io.Serializable;

import com.fulan.api.calendar.domain.CalendarBook;

import lombok.Data;
/**
 * <p>
 * 日历详情Vo
 * </p>
 *
 */
@Data
public class CalendarBookVo implements Serializable{

	private static final long serialVersionUID = 660878466609008500L;
	
	private CalendarBook calendarBook;
	//操作人姓名
	private String operateUserName;
}
