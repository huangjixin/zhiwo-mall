package com.fulan.application.controller;

import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarDailyTransactionService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Api(tags = "CalendarBookApi", description = "日历控制层")
@Controller
@RequestMapping("/calendarBook")
public class CalendarBookController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarBookController.class);

    @Autowired
    private CalendarDailyTransactionService dailyTransactionService;

    @ApiOperation(value = "查询日历", notes = "查询日历", response = Response.class)
    @PostMapping("findCalendarByCalendarDate")
    @ResponseBody
    public Response<Object> findCalendarByCalendarDate(@RequestParam String calendarDate,
                                                       @RequestParam String agentCode,
                                                       @RequestParam String orgId){
        try {
            Map<String, List<CalendarDailyTransaction>> map = dailyTransactionService.selectByCalendarDate(calendarDate,agentCode,orgId);

            Map<String,Object> result = new HashMap<>();
            result.put("result", map);
            result.put("nowDate", DateUtil.toDay(new Date()));

            Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");
            response.setData(result);
            return response;
        }catch (Exception e){
            logger.error("----查询日历失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询日历失败");
        }
    }

}