package com.fulan.application.controller;

import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.service.CalendarTransactionService;
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

import java.util.*;

@Controller
@RequestMapping("/calendarTransaction")
public class CalendarTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarBookController.class);

    @Autowired
    private CalendarTransactionService calendarTransactionService;

    @ApiOperation(value = "添加事务", notes = "添加事务", response = Response.class)
    @PostMapping("saveTransactionByCalendar")
    @ResponseBody
    public Response<Object> saveTransactionByCalendar(CalendarTransactionMemberListVO memberListVO){
        try {
           calendarTransactionService.saveTransactionByCalendar(memberListVO);
            return new Response<>(Response.SUCCESS,"添加事务成功");
        }catch (Exception e){
            logger.error("----添加事务失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"添加事务失败");
        }
    }

    @ApiOperation(value = "查询事务", notes = "查询事务", response = Response.class)
    @PostMapping("findTransactionByCalendarDate")
    @ResponseBody
    public Response<Object> findTransactionByCalendarDate(Integer calendarDate){
        try {
            if (calendarDate == null){
                return new Response<>(Response.ERROR,"calendarDate不能为空");
            }
            List<CalendarTransaction> transactions = calendarTransactionService.findTransactionByCalendarDate(calendarDate);

            Map<String, Object> map = new HashMap<>();
            Map<String, List<CalendarTransaction>> result = new HashMap<>();
            for (CalendarTransaction calendarTransaction :transactions){
                String date = DateUtil.toMonthAndDay(calendarTransaction.getDate());
                if(result.containsKey(date)){
                    List<CalendarTransaction> transactionList = result.get(date);
                    transactionList.add(calendarTransaction);
                    result.put(date, transactionList);
                }else{
                    List<CalendarTransaction> transactionList = new ArrayList<>();
                    result.put(date, transactionList);
                }
            }
            map.put("transactionList", result);
            map.put("newDate", DateUtil.toSeconds(new Date()));
            Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");
            response.setData(map);
            return response;
        }catch (Exception e){
            logger.error("----查询事务失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询事务失败");
        }
    }

    @ApiOperation(value = "查询事务详情", notes = "查询事务详情", response = Response.class)
    @PostMapping("findTransactionById")
    @ResponseBody
    public Response<Object> findTransactionById(Long id){
        try {
            if (id == null){
                return new Response<>(Response.ERROR,"id不能为空");
            }
            Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");
            response.setData(calendarTransactionService.findTransactionById(id));
            return response;
        }catch (Exception e){
            logger.error("----查询事务详情失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询事务详情失败");
        }
    }

}
