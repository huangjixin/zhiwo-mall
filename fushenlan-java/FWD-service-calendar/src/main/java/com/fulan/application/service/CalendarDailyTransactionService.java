package com.fulan.application.service;

import com.fulan.api.calendar.domain.CalendarDailyTransaction;

import java.util.List;

public interface CalendarDailyTransactionService {

    List<CalendarDailyTransaction> selectByCalendarId(Integer calendarDate);

	void addCalendarDailyTransactionList(List<CalendarDailyTransaction> dailyTransactionList);
    
    
}