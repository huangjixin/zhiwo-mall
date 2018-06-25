package com.fulan.application.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.application.service.AttendanceRulesService;
import com.fulan.application.service.CalendarDailyTransactionService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: xuanwugang
 * @Date: 2018/4/9 09:16
 * 考勤规则前端控制器
 */

@Api(tags = "ManageAttendanceRuleApi", description = "考勤管理层")
@RestController
@RequestMapping(value ="/manage/attendanceRules")
public class ManageAttendanceRuleController {
	
	@Autowired
	private AttendanceRulesService attendanceRulesService;
	
	@Autowired
	private CalendarDailyTransactionService calendarDailyTransactionService ;
	

	/**
     * 查询考勤规则Vo
     */
	@ApiOperation(value = "查询考勤规则Vo", notes = "查询考勤规则Vo", response = Response.class)
	@RequestMapping(value ="/getAttendanceRulesVoById")
	public Response<AttendanceRulesVo> getAttendanceRulesVoById(
			@RequestParam(value="attendanceRulesId",required=false)long attendanceRulesId
			){
		try{
			return attendanceRulesService.getAttendanceRulesById(attendanceRulesId);
		}catch(Exception e){
			return new Response<AttendanceRulesVo>(Response.ERROR,"获取考勤规则失败");
		}
	}
	
	/**
     * 新增或修改考勤规则Vo
     * 
     */
	@ApiOperation(value = "新增或修改考勤规则Vo", notes = "新增或修改考勤规则Vo", response = Response.class)
	@RequestMapping(value ="/addOrUpdateAttendanceRules")
	public Response<Integer> addOrUpdateAttendanceRules(
			@RequestBody AttendanceRulesVo attendanceRulesVo
			){
		try{
			return attendanceRulesService.addOrUpdateAttendanceRulesVo(attendanceRulesVo);
		}catch(Exception e){
			return new Response<Integer>(Response.ERROR,"新增或修改考勤失败");
		}
	}
	
	/**
     * 删除考勤规则Vo
     * 
     */
	@ApiOperation(value = "删除考勤规则Vo", notes = "删除考勤规则Vo", response = Response.class)
	@RequestMapping(value ="/deleteAttendanceRules")
	public Response<Integer> deleteAttendanceRules(
			@RequestParam(value="attendanceRulesId",required=true)long attendanceRulesId
			){
		try{
			return attendanceRulesService.deleteAttendanceRules(attendanceRulesId);
		}catch(Exception e){
			return new Response<Integer>(Response.ERROR,"删除考勤失败");
		}
	}
	
	/**
     * 查询所有基础或活动考勤的每日详情
     * 
     */
	@ApiOperation(value = "查找所有基础或活动考勤的每日详情", notes = "查找所有基础或活动考勤的每日详情", response = Response.class)
	@RequestMapping(value ="/findAllDailyTransaction")
	public Response<List<CalendarDailyTransaction>> findAllDailyTransaction(
			@RequestParam(value="rulesType",required=false)int rulesType){
		try{
			Response<List<CalendarDailyTransaction>> resp = new Response<List<CalendarDailyTransaction>>(Response.SUCCESS,"查询所有每日详情成功");
			resp.setData(calendarDailyTransactionService.findAllDailyTransaction(rulesType));
			return resp;
		}catch(Exception e){
			return new Response<List<CalendarDailyTransaction>>(Response.ERROR,"查询所有每日详情失败");
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
