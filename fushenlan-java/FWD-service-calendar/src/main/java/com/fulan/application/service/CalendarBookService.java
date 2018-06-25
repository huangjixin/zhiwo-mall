package com.fulan.application.service;

import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.vo.CalendarBookDetailVo;
import com.fulan.api.calendar.vo.CalendarBookVo;
import com.fulan.application.util.page.PageInfo;

public interface CalendarBookService {

	PageInfo<CalendarBookVo> findCalendarByName(String calendarName, int pageNo, int pageSize);

	CalendarBookDetailVo findCalendarById(String calendarId);
	
	int findMaxMatainMonth(); 

	CalendarBook findCalendarByCalendarDate(Integer calendarDate);

	void addCalendarBook(CalendarBook calendarBook);
}