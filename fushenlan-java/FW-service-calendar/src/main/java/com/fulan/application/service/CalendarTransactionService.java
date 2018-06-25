package com.fulan.application.service;

import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import java.util.List;
import java.util.Map;

public interface CalendarTransactionService {

    List<CalendarTransaction> findTransactionByCalendarDate(String calendarDate,String agentCode);

	List<Map<String,Object>>  findTransactionByCalendarDateNew(String agentCode,String calendarDate);

    void saveTransactionByCalendar(CalendarTransactionMemberListVO memberListVO);

    CalendarTransactionMemberListVO findTransactionById(Long id);
    
    /**
     * 分页条件查询事物消息
     * @param transactionParams 事物条件Map
     * @return
     */
    PageInfo<CalendarTransaction> getTransactionByPage(Map<String, Object> transactionParams);
    
    /**
     * 根据Id移除事物
     * @param id
     * @return
     */
    Response<Boolean> removeTransactionById(Long id);
    
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

	/**
	 * 根据Id获取事物对象
	 * @param id
	 * @return
	 */
	Response<CalendarTransaction> getCalendarTransactionById(Long id);

    /**
     * 查询代理人的事务信息，现在的场景是用来做消息推送需要
     * @param agentCode
     * @return 代理人的事务信息
     */
    Response<List<CalendarTransaction>> findTransactionByAgentCode(String agentCode);
	/**
	 * 获取当前客户的拜访记录
	 * @param id
	 * @return
	 */
	List<CalendarTransaction> getfindCustomerVisit(Map<String,Object> params);
	
	/**
	 * 根据Id获取事物对象 方便反显
	 * @param id
	 * @return
	 */
	
	CalendarTransaction selectById(Long id);

}