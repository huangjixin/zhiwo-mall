package com.fulan.application.controller;

import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.service.AttendanceHistoryService;
import com.fulan.application.service.CalendarDailyTransactionService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.date.Lauar;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考勤控制层
 */
@Api(tags = "AttendanceApi", description = "考勤控制层")
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    private CalendarDailyTransactionService dailyTransactionService;
    @Autowired
    private AttendanceHistoryService attendanceHistoryService;

    @Autowired
    private DictionaryService dictionaryService;
    @ApiOperation(value = "查询考勤", notes = "查询考勤", response = Response.class)
    @PostMapping("findCalendarByCalendarDate")
    @ResponseBody
    public Response<Object> findAttendanceByCalendarDate(String calendarDate,String agentCode,String orgId,String companyId){
        try {
            Integer count = dailyTransactionService.selectCountByCalendarDate(calendarDate,agentCode,orgId);
            Integer historyCount = attendanceHistoryService.selectCountByCalendarDate(calendarDate,agentCode,orgId);

            Map<String,Object> result = new HashMap<>();
            result.put("allHistory", attendanceHistoryService.selectAllHistory(calendarDate,agentCode,orgId,companyId));
            result.put("count", count);//应出勤
            result.put("historyCount", historyCount);//实际出勤
            result.put("noCount", count - historyCount);//未出勤
            result.put("nowDate", DateUtil.toDay(new Date()));//阳历
            result.put("lunarCalendar", Lauar.getLunar(new Date()));//农历
            result.put("week", DateUtil.getChineseWeekName(new Date()));//星期几

            Response<Object> response = new Response<>(Response.SUCCESS,"查询考勤成功");
            response.setData(result);
            return response;
        }catch (Exception e){
            logger.error("----查询考勤失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询考勤失败");
        }
    }

    @ApiOperation(value = "考勤打卡", notes = "考勤打卡", response = Response.class)
    @PostMapping("insertAttendance")
    @ResponseBody
    public Response<Object> insertAttendance(@RequestBody AttendanceHistory attendanceHistory,String ip){
        try {
            List<Dictionary> list = dictionaryService.findByPCodeAndValue("ip_whiteList",ip);
            if(list==null||list.size()<=0)
                return new Response<>(Response.ERROR,"错误的ip");
            attendanceHistoryService.addAttendanceHistory(attendanceHistory);
            return new Response<>(Response.SUCCESS,"打卡成功");
        }catch (Exception e){
            logger.error("----查询考勤失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"打卡成功");
        }
    }
}