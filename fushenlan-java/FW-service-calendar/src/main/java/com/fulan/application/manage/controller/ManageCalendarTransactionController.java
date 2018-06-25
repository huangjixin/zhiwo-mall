package com.fulan.application.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.application.service.CalendarTransactionService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@Controller
@RequestMapping(value ="/manage/calendarTransaction")
public class ManageCalendarTransactionController {
	@Autowired
	private CalendarTransactionService  calendarTransactionService;
	

	@GetMapping("/getTransactionByPage")
	@ResponseBody
	public Response<PageInfo<CalendarTransaction>> getTransactionByPage(@RequestParam(value= "theme",required = false) String theme, @RequestParam(value= "state",required = false)String state, @RequestParam(value= "startTime",required = false)String startTime,
			@RequestParam(value= "endTime",required = false)String endTime, @RequestParam("pageNo")Integer pageNo, @RequestParam("pageSize")Integer pageSize) {

		try {
			//构建查询参数
			Map<String, Object> transactionParams = new HashMap<>();
			transactionParams.put("theme", theme);
			transactionParams.put("state", state);
			transactionParams.put("startTime", startTime);
			transactionParams.put("endTime", endTime);
			transactionParams.put("pageNo", pageNo);
			transactionParams.put("pageSize", pageSize);
			Response<PageInfo<CalendarTransaction>> response = new Response<>();
			response.setData(calendarTransactionService.getTransactionByPage(transactionParams));
			response.setCode(Response.SUCCESS);
			response.setMsg(Response.SUCCESS_MESSAGE);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
		}

	}
	
	@PostMapping("/removeTransactionById")
	@ResponseBody
	public Response<Boolean> removeTransactionById(@RequestParam(value = "id",required = false) Long id) {
		return calendarTransactionService.removeTransactionById(id);
	}
	
	@PostMapping("/editTransaction")
	@ResponseBody
	public Response<Boolean> editTransaction(
			@RequestBody(required = false) CalendarTransaction calendarTransaction){
		return calendarTransactionService.editTransaction(calendarTransaction);
	}
	
	@PostMapping("/addTransaction")
	@ResponseBody
	public Response<Boolean> addTransaction(@RequestBody(required = false) CalendarTransaction calendarTransaction){
		return calendarTransactionService.addTransaction(calendarTransaction);
	}
	
	@GetMapping("/getCalendarTransactionById")
	@ResponseBody
	public CalendarTransaction getCalendarTransactionById(@RequestParam(value = "id",required = false) Long id){
		return calendarTransactionService.selectById(id);
	}
	
	
}
