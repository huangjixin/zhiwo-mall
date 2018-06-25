package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.fulan.api.calendar.domain.CalendarDailyTransaction;

public interface CalendarDailyTransactionService {

    Map<String,List<CalendarDailyTransaction>> selectByCalendarDate(String calendarDate,String agentCode,String orgId);

    Integer selectCountByCalendarDate(String calendarDate,String agentCode,String orgId);

    void addCalendarDailyTransactionList(List<CalendarDailyTransaction> dailyTransactionList);

	List<CalendarDailyTransaction> findAllDailyTransaction(int rulesType);
}