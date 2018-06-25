package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.CalendarTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CalendarTransactionMapper extends BaseMapper<CalendarTransaction> {

	/**
	 * 条件分页查询事物
	 * @param page 分页对象
	 * @param params 参数Map
	 * @return
	 */
	List<CalendarTransaction> selectTransactionByPage(Page<CalendarTransaction> page,Map<String,Object> params);

	/**
	 * 根据代理人查询相关联的事务消息
	 * @param paramMap
	 * @return
	 */
	List<CalendarTransaction> selectTransactionByAgents(Map<String,Object> paramMap);

	List<CalendarTransaction> findTransactionByCalendarDate(@Param("agentCode")String agentCode, @Param("calendarDate")String calendarDate);
	
	int selectAttendanceRulesCountByParams(Map<String,Object> params);
	/**
	 * 查询客户拜访记录
	 * @param params
	 * @return
	 */
	List<CalendarTransaction> findCustomerCalendarDate(Map<String,Object> params);
	
	
	CalendarTransaction selectCalendarTransactionById(@Param(value="id")Long id);
}