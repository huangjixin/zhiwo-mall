package com.fulan.application.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsNewsVO;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 消息池控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsNewsApi", description = "消息池接口")
@Controller
@RequestMapping("/manage/smsNews")
public class SmsNewsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsNewsController.class);

    @Autowired
    private SmsNewsService smsNewsService;

    @ApiOperation(value = "查询消息池列表", notes = "查询消息池列表", response = Response.class)
    @PostMapping("selectSmsNews")
    @ResponseBody
    public Page<SmsNewsResultVO> selectSmsNews(@RequestBody SmsNewsVO smsNewsVO){
        try {
            return smsNewsService.selectSmsNews(smsNewsVO);
        }catch (Exception e){
            logger.error("----查询消息池列表失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "查询消息详情", notes = "查询消息详情", response = Response.class)
    @PostMapping("selectSmsNewsById")
    @ResponseBody
    public SmsNewsResultVO selectSmsNewsById(@RequestParam Long id, Long status){
        try {
            return smsNewsService.selectSmsNewsById(id,status);
        }catch (Exception e){
            logger.error("----查询消息详情失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "统计", notes = "统计", response = Response.class)
    @PostMapping("selectSmsNewsStatistics")
    @ResponseBody
    public Page<SmsNewsStatisticsResultVO> selectSmsNewsStatistics(@RequestBody SmsNewsStatisticsVO smsNewsStatisticsVO){
        try {
            return smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
        }catch (Exception e){
            logger.error("----查询消息详情失败----");
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "添加消息", notes = "添加消息", response = Response.class)
    @PostMapping("saveSmsNews")
    @ResponseBody
    public Response<String> saveSmsNews(@RequestBody SmsNewsResultVO smsNewsVO){
        try {
            smsNewsService.saveSmsNews(smsNewsVO);
        }catch (Exception e){
            logger.error("----添加消息失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"添加消息失败");
        }
        return new Response<>(Response.SUCCESS,"添加消息成功");
    }
    
    
    @ApiOperation(value = "查询消息池列表", notes = "查询消息池列表", response = Response.class)
    @GetMapping("setlectSmsNewsList")
    @ResponseBody
    public PageInfo<SmsNewsResultVO> setlectSmsNewsList(
    		@RequestParam(value="content",required=false) String content,
     		@RequestParam(value="phone",required=false) String phone,
     		@RequestParam(value="status",required=false) String status,
     		@RequestParam(value="systemCode",required=false) String systemCode,
     		@RequestParam(value="channelCode",required=false) String channelCode,
     		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize,
            @RequestParam(value="type",required=false) String type
    		){
        try {
        	Page<SmsNewsResultVO> page = new Page<SmsNewsResultVO>(pageNo, pageSize);
        	
        	PageInfo<SmsNewsResultVO> pageInfo =smsNewsService.setlectSmsNewsList(page,content,phone,status,systemCode,channelCode,pageNo,pageSize,type);
        	
            return pageInfo;
        }catch (Exception e){
            logger.error("----查询消息池列表失败----");
            e.printStackTrace();
            return null;
        }
    }
    
    
}