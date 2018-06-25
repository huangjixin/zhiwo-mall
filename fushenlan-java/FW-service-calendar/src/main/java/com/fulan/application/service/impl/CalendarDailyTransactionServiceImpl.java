package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.application.config.Contant;
import com.fulan.application.mapper.CalendarDailyTransactionMapper;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarDailyTransactionService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.util.JsonUtils;
import com.fulan.application.util.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CalendarDailyTransactionServiceImpl extends ServiceImpl<CalendarDailyTransactionMapper, CalendarDailyTransaction> implements CalendarDailyTransactionService {

    @Autowired
    private CalendarBookService calendarBookService;
    @Autowired
    private CalendarDailyTransactionMapper dailyTransactionMapper;

    @Override
    public Map<String,List<CalendarDailyTransaction>> selectByCalendarDate(String calendarDate,String agentCode,String orgId) {
        if (StringUtils.isEmptry(calendarDate)){
            //为空默认获取当月
            calendarDate = DateUtil.toDay(new Date());
        }
        List<CalendarDailyTransaction> transactions =
                dailyTransactionMapper.findCalendarByCalendarDate(agentCode,orgId,calendarDate);
        return JsonUtils.listToMap(transactions, Contant.DAY);
    }

    @Override
    public Integer selectCountByCalendarDate(String calendarDate,String agentCode,String orgId) {
        if (StringUtils.isEmptry(calendarDate)){
            //为空默认获取当月
            calendarDate = DateUtil.toDay(new Date());
        }
        return dailyTransactionMapper.selectCountByCalendarDate(agentCode,orgId,calendarDate);
    }

	@Override
	public void addCalendarDailyTransactionList(List<CalendarDailyTransaction> dailyTransactionList) {
				this.insertBatch(dailyTransactionList);
	}

	@Override
	public List<CalendarDailyTransaction> findAllDailyTransaction(int rulesType) {
		return dailyTransactionMapper.selectList(new EntityWrapper<CalendarDailyTransaction>().eq("attendance_type", rulesType));
	}

}