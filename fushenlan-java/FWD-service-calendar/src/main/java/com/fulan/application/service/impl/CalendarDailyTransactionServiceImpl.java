package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.application.mapper.CalendarDailyTransactionMapper;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarDailyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarDailyTransactionServiceImpl extends ServiceImpl<CalendarDailyTransactionMapper, CalendarDailyTransaction> implements CalendarDailyTransactionService {

    @Autowired
    private CalendarBookService calendarBookService;

    @Override
    public List<CalendarDailyTransaction> selectByCalendarId(Integer calendarDate) {
        CalendarBook calendarBook = calendarBookService.findCalendarByCalendarDate(calendarDate);
        CalendarDailyTransaction dailyTransaction = new CalendarDailyTransaction();
        dailyTransaction.setCalendarId(calendarBook.getId());
        return selectList(new EntityWrapper<>(dailyTransaction));
    }

	@Override
	public void addCalendarDailyTransactionList(List<CalendarDailyTransaction> dailyTransactionList) {
				this.insertBatch(dailyTransactionList);
	}
}