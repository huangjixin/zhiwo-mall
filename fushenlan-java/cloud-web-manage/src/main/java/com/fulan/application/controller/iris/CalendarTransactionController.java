package com.fulan.application.controller.iris;


import org.apache.shiro.session.mgt.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.service.ManageCalendarTransactionService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/calendarTransaction")
public class CalendarTransactionController {
	@Autowired
	private ManageCalendarTransactionService manageCalendarTransactionService;
	
	
	@GetMapping("/getTransactionByPage")
	public String getTransactionByPage(Model model, @RequestParam(value = "theme", required = false) String theme,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "pageNo",required=false) String pageNo,
			@RequestParam(value = "pageSize",required = false) String pageSize) {
		String strEmpty = "";
		pageNo = StringUtil.isEmpty(pageNo)? "1" : pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)? "10" : pageSize;
		theme = StringUtil.isEmpty(theme)? strEmpty : theme;
		state = StringUtil.isEmpty(state)? strEmpty : state;
		startTime = StringUtil.isEmpty(startTime)? strEmpty : startTime;
		endTime = StringUtil.isEmpty(endTime)? strEmpty : endTime;
		Response<PageInfo<CalendarTransaction>> res = manageCalendarTransactionService.getTransactionByPage(theme.trim(),state,
				startTime, endTime, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		PageInfo<CalendarTransaction> page = res.getData();
		model.addAttribute("page", page);
		model.addAttribute("theme", theme);
		model.addAttribute("state", state);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("data", res.getData());
		return "iris/calendar/calendar_list";
	}

	@PostMapping("/removeTransactionById")
	@ResponseBody
	public Response<Object> removeTransactionById(@RequestParam(value = "id", required = false) Long id) {
			return manageCalendarTransactionService.removeTransactionById(id);
	}
	

	@PostMapping("/editTransaction")
	@ResponseBody
	public Response<Boolean> editTransaction(CalendarTransaction calendarTransaction) {
		calendarTransaction.setModifyUser(SessionContextUtils.getCurrentUserId());
		if(null != calendarTransaction.getSendType() && calendarTransaction.getSendType().equals("1")){
			calendarTransaction.setCompanyId(SessionContextUtils.getCurrentUser().getCompanyId());
		}
		return manageCalendarTransactionService.editTransaction(calendarTransaction);
	}

	@PostMapping("/addTransaction")
	@ResponseBody
	public Response<Boolean> addTransaction(CalendarTransaction calendarTransaction) {
		calendarTransaction.setCreateUser(SessionContextUtils.getCurrentUserId());
		if(calendarTransaction.getSendType().equals("1")){
			calendarTransaction.setCompanyId(SessionContextUtils.getCurrentUser().getCompanyId());
		}
		return manageCalendarTransactionService.addTransaction(calendarTransaction);
	}
	
	@GetMapping("/getTransactionByPage/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<CalendarTransaction>> slistByPageAjax(@RequestParam(value = "theme", required = false) String theme,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime, @RequestParam("pageNo") String pageNo,
			@RequestParam("pageSize") String pageSize) {
		pageNo = StringUtil.isEmpty(pageNo)? "1":pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)? "10":pageSize;
		Response<PageInfo<CalendarTransaction>> res = manageCalendarTransactionService.getTransactionByPage(theme.trim(),state,
				startTime, endTime, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		return res;
	}
	
/*	@GetMapping("/getCalendarTransactionById")
	@ResponseBody
	public Response<CalendarTransaction> getCalendarTransactionById(Long id){
		return manageCalendarTransactionService.getCalendarTransactionById(id);
	}*/
	
	@GetMapping("/toCalendarTransactionEditPage")
	public String toCalendarTransactionEditPage(Model model,String id ,String state){
		if(!StringUtils.isEmpty(id)){
			model.addAttribute("id", id);
			CalendarTransaction  calendarTransaction= manageCalendarTransactionService.getCalendarTransactionById(Long.valueOf(id));
			model.addAttribute("calendarTransaction",calendarTransaction);
		}
		model.addAttribute("state",state);
		return "iris/calendar/calendar_edit";
	}
}
