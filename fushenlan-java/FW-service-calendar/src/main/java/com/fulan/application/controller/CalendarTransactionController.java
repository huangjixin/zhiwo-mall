package com.fulan.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.service.CalendarTransactionService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "CalendarTransactionApi", description = "日历事务控制层")
@Controller
@RequestMapping("/calendarTransaction")
public class CalendarTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarBookController.class);

    @Autowired
    private CalendarTransactionService calendarTransactionService;
    


    @ApiOperation(value = "添加事务", notes = "添加事务", response = Response.class)
    @PostMapping("saveTransactionByCalendar")
    @ResponseBody
    public Response<Object> saveTransactionByCalendar(@RequestBody CalendarTransactionMemberListVO memberListVO){
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
    public Response<Object> findTransactionByCalendarDate(@RequestParam String agentCode,
                                                          @RequestParam String calendarDate){
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("result", calendarTransactionService.findTransactionByCalendarDateNew(agentCode,calendarDate));
            result.put("newDate", DateUtil.toSeconds(new Date()));

            Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");
            response.setData(result);
            return response;
        }catch (Exception e){
            logger.error("----查询事务失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询事务失败");
        }
    }
    @ApiOperation(value = "客户拜访记录查询", notes = "客户拜访记录查询", response = Response.class)
    @PostMapping("findCustomerCalendarDate")
    @ResponseBody
    public Response<Object> findCustomerCalendarDate(
    												 @ApiParam(name="params",value="{Keyword：搜索关键字,agentCode(必传):代理人编号,customerID(必传):代理人下的当前客户ID}")
    												 @RequestBody(required=false) Map<String, Object> params 
    												 
    												){
    	Map<String, Object> paramMap = new HashMap<>();
    	//获得代理人Code
    	paramMap.put("agentCode", params.get("agentCode"));
    	//事物类型
    	paramMap.put("transactionType", 2);
    	//获得当前时间
    	paramMap.put("transactionFutureTime", new Date());
    	//获得用户编号
    	paramMap.put("transactionRefCustomer", params.get("customerID"));
    	//关键字查询
    	paramMap.put("Keyword", params.get("Keyword"));
    	
    	try {
    		Map<String, Object> result = new HashMap<>();
    		if( params.get("agentCode") != null  && params.get("agentCode") != ""  
    			&& params.get("customerID") != null  && params.get("customerID")!= "") {
    			List<CalendarTransaction> customerTransaction= calendarTransactionService.getfindCustomerVisit(paramMap);
    			result.put("result", customerTransaction);
    		}
    		
    		Response<Object> response = new Response<>(Response.SUCCESS,"查询成功");
    		response.setData(result);
    		return response;
    	}catch (Exception e){
    		logger.error("----查询事务失败----");
    		e.printStackTrace();
    		return new Response<>(Response.ERROR,"查询事务失败");
    	}
    }
    @ApiOperation(value = "获取参会人员", notes = "获取参会人员", response = Response.class)
    @PostMapping("getAttendees")
    @ResponseBody
    public Response<String> getAttendees(@ApiParam(name = "agentCode",value = "代理人编号",example = "代理人编号")
	@RequestParam(name = "agentCode",required = false) String agentCode) {
		// TODO Auto-generated method stub
		Response<String> resp = new Response<String>(Response.SUCCESS, "获得参会人员");
		resp.setData(JSONObject.parseObject(readJson("attendees.json")).toJSONString());
		resp.setCode("1");
		return  resp;	
		}
    String readJson(String fileName) {
		try {

			InputStream stream = getClass().getClassLoader().getResourceAsStream("jsonpage/"+fileName);
			byte []bts=new byte[1024*24];
			stream.read(bts);
			return new String(bts,"UTF-8");

		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code",0);
			jsonObject.put("data","{}");
			jsonObject.put("erro",e.getMessage());
			e.printStackTrace();
			return jsonObject.toJSONString();

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

    /**
     * @Author Lycol for support sending message list to APP (我的消息列表模块)
     * @return
     */
    @ApiOperation(value = "查询事务消息列表", notes = "查询事务消息列表",responseContainer ="List", response = Response.class)
    @GetMapping("findTransactionMessageList")
    @ResponseBody
    public Response<List<CalendarTransaction>> findTransactionMessageList(String agentCode){
        try {

            Response<List<CalendarTransaction>> response = new Response<List<CalendarTransaction>>(Response.SUCCESS,"查询成功");

            return calendarTransactionService.findTransactionByAgentCode(agentCode);
        }catch (Exception e){
            logger.error("----查询事务详情失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"查询事务详情失败");
        }
    }

}
