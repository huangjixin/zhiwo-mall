package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.util.domain.Response;

import java.util.List;
import java.util.Map;

public interface CalendarTransactionService {

    List<CalendarTransaction> findTransactionByCalendarDate(Integer calendarDate);

    void saveTransactionByCalendar(CalendarTransactionMemberListVO memberListVO);

    CalendarTransactionMemberListVO findTransactionById(Long id);
    
    /**
     * 分页条件查询事物消息
     * @param transactionParams 事物条件Map
     * @return
     */
    Page<CalendarTransaction> getTransactionByPage(Map<String, Object> transactionParams);
    
    /**
     * 根据Id移除事物
     * @param id
     * @return
     */
    Boolean removeTransactionById(Long id);
    
    /**
     * 根据事物对象修改信息
     * @param calendarTransaction
     */
    Response<Boolean> editTransaction(CalendarTransaction calendarTransaction);

    /**
     * 添加事物
     * @param calendarTransaction
     * @return
     */
	Response<Boolean> addTransaction(CalendarTransaction calendarTransaction);
    
}