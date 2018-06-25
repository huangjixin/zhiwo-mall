package com.fulan.application.manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.api.calendar.vo.CalendarBookDetailVo;
import com.fulan.application.service.AttendanceObjectsService;
import com.fulan.application.service.AttendanceRulesService;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarDailyTransactionService;

/**
 * @Author: xuanwugang
 * @Date: 2018/4/9 09:16
 * 新增考勤规则
 */
@Controller
@RequestMapping(value ="/manage/attendanceRules")
public class ManageAttendanceRuleController {
	
	@Autowired
	private CalendarBookService calendarBookService;
	
	@Autowired
	private CalendarDailyTransactionService calendarDailyTransactionService;
	
	@Autowired
	private AttendanceObjectsService attendanceObjectsService;
	
	@Autowired
	private AttendanceRulesService attendanceRulesService;
	

	/**
     * 查询考勤规则Vo
     * 
     */
	@RequestMapping(value ="/getAttendanceRulesVoById")
	public AttendanceRulesVo getAttendanceRulesVoById(
			@RequestParam(value="attendanceRulesId",required=false)long attendanceRulesId
			){
		try{
			//查询考勤规则
			AttendanceRules attendanceRules = attendanceRulesService.getAttendanceRulesById(attendanceRulesId);
			//查询考勤对象
			List<AttendanceObjects> attendanceObjects = attendanceObjectsService.getListByAttendanceRulesId(attendanceRules.getId());
			//查询日历及详情
			CalendarBookDetailVo calendarBookDetailVo = calendarBookService.findCalendarById(attendanceRules.getCalendarId().toString());
			//组装返回Vo
			AttendanceRulesVo AttendanceRulesVo = new AttendanceRulesVo();
			AttendanceRulesVo.setAttendanceObjectsList(attendanceObjects);
			AttendanceRulesVo.setAttendanceRules(attendanceRules);
			AttendanceRulesVo.setCalendarBook(calendarBookDetailVo.getCalendarBook());
			AttendanceRulesVo.setDailyTransactionList(calendarBookDetailVo.getCalendarDailyTransactionList());
			return AttendanceRulesVo;
		}catch(Exception e){
			return null;
		}
	}
	
	/**
     * 新增考勤规则
     * 
     */
	@RequestMapping(value ="/addAttendanceRule")
	public Boolean addAttendanceRule(
			@RequestParam(value="attendanceRulesVo",required=false)AttendanceRulesVo attendanceRulesVo
			){
		try{
			//插入考勤规则及考勤对象
			AttendanceRules attendanceRules = attendanceRulesVo.getAttendanceRules();
			List<AttendanceObjects> attendanceObjectsList = attendanceRulesVo.getAttendanceObjectsList();
			attendanceRulesService.addAttendanceRules(attendanceRules);
			attendanceObjectsService.addAttendanceObjectsList(attendanceObjectsList);
			//插入日历及每日详情
			CalendarBook calendarBook = attendanceRulesVo.getCalendarBook();
			List<CalendarDailyTransaction> dailyTransactionList = attendanceRulesVo.getDailyTransactionList();
			calendarBookService.addCalendarBook(calendarBook);
			calendarDailyTransactionService.addCalendarDailyTransactionList(dailyTransactionList);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	
	/**
     * 日历查询页
     */
	/*@RequestMapping(value ="/listCalendar")
	public String listCalendar(
			Map<String,Object> map,
			@RequestParam(value="calendarName",required=false)String calendarName,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize
			){
		
		PageInfo<CalendarBookVo> pageInfo = CalendarBookService.findCalendarByName(calendarName,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		//查询最大维护月份
		int maxMatainMonth = CalendarBookService.findMaxMatainMonth();
		map.put("maxMatainMonth", maxMatainMonth);
		map.put("pageInfo", pageInfo);
		return "/calendar_list";
	}*/
	
	/**
     * 日历查询页ajax
     */
	/*@RequestMapping(value ="/listCalendarAjax")
	@ResponseBody
	public PageInfo<CalendarBookVo> listCalendarAjax(
			@RequestParam(value="calendarName",required=false)String calendarName,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize
			){
		PageInfo<CalendarBookVo> pageInfo = CalendarBookService.findCalendarByName(calendarName,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		return pageInfo;
	}*/
	
	/**
     * 日历新增和查看详情页
     */
	/*@RequestMapping(value ="/toAddCalendar")
	public String toAddCalendar(
			Map<String,Object> map,
			@RequestParam(value="calendarId",required=false)String calendarId
			){
		CalendarBookDetailVo calendarBookDetailVo = CalendarBookService.findCalendarById(calendarId);
		map.put("calendarBookDetailVo", calendarBookDetailVo);
		return "/calendar_add";
	}*/
	
	/**
     * 日历新增ajax方法
     */
	/*@RequestMapping(value ="/addCalendar")
	@ResponseBody
	public boolean addCalendar(@RequestBody CalendarBookDetailVo CalendarBookDetailVo){
		try{
			CalendarBookService.addCalendar(calendarBookDetailVo);
			calendarDailyTransactionService.addCalendar(CalendarBookDetailVo);
			return true;
		}catch(Exception e){
			return false;
		}
	}*/
}



