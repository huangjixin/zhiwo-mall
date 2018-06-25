package com.fulan.api.calendar.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>消息管理服务</p>
 * @author Administrator
 * @creatTime 2018年4月13日11:15:07
 */
@FeignClient(name = "calendar")
public interface ManageCalendarTransactionService {
	@GetMapping("/manage/calendarTransaction/getTransactionByPage")
	public Response<PageInfo<CalendarTransaction>> getTransactionByPage(@RequestParam(value= "theme",required = false) String theme, @RequestParam(value= "state",required = false)String state, @RequestParam(value= "startTime",required = false)String startTime,
			@RequestParam(value= "endTime",required = false)String endTime, @RequestParam("pageNo")Integer pageNo, @RequestParam("pageSize")Integer pageSize);
	
	@PostMapping("/manage/calendarTransaction/removeTransactionById")
	public Response<Object> removeTransactionById(@RequestParam(value = "id",required = false) Long id);
	
	@PostMapping("/manage/calendarTransaction/editTransaction")
	public Response<Boolean> editTransaction(@RequestBody(required = false) CalendarTransaction calendarTransaction);
	
	@PostMapping("/manage/calendarTransaction/addTransaction")
	public Response<Boolean> addTransaction(@RequestBody(required = false) CalendarTransaction calendarTransaction);
	
	@GetMapping("/manage/calendarTransaction/getCalendarTransactionById")
	public CalendarTransaction getCalendarTransactionById(@RequestParam(value = "id",required = false)Long id);
}
