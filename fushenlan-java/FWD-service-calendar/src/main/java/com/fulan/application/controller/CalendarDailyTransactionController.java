package com.fulan.application.controller;

import com.fulan.application.service.CalendarBookService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/calendarDailyTransaction")
public class CalendarDailyTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarBookController.class);

    @Autowired
    private CalendarBookService calendarBookService;

    @ApiOperation(value = "查询日历", notes = "查询日历", response = Response.class)
    @PostMapping("findCalendarByCalendarDate")
    @ResponseBody
    public Response<Object> findCalendarByCalendarDate(Integer calendarDate){
        try {
            if (calendarDate == null){
                return new Response<>(Response.ERROR,"calendarDate不能为空");
            }
            Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");

            Map<String,Object> result = new HashMap<>();
            result.put("detailVO", calendarBookService.findCalendarByCalendarDate(calendarDate));
            result.put("nowDate", DateUtil.toDay(new Date()));

            response.setData(result);
            return response;
        }catch (Exception e){
            logger.error("----查询日历失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询日历失败");
        }
    }
}