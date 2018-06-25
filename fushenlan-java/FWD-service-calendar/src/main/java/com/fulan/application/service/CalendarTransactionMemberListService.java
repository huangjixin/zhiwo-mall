package com.fulan.application.service;

import com.fulan.api.calendar.domain.CalendarTransactionMemberList;

import java.util.List;

public interface CalendarTransactionMemberListService {

    List<CalendarTransactionMemberList> selectByTransactionId(Long transactionId);

    void saveCalendarTransactionMemberList(List<CalendarTransactionMemberList> memberList);
}