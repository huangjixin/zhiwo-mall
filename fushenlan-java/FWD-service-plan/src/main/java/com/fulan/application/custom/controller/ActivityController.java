package com.fulan.application.custom.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.api.plan.vo.OfflineActivityDto;
import com.fulan.api.plan.vo.OfflineActivityVO;
import com.fulan.api.plan.vo.PageResultVO;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.OfflineActivityEnterService;
import com.fulan.application.service.OfflineActivityService;
import com.fulan.application.service.OfflineActivitySignService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fsl on 2018/1/23.
 */
@Api(tags = "ActivityApi", description = "线下活动接口")
@RestController
@RequestMapping(value = "/customer/activity")
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private OfflineActivitySignService offlineActivitySignService;
    @Autowired
    private OfflineActivityService offlineActivityService;
    @Autowired
    private OfflineActivityEnterService offlineActivityEnterService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "线下活动报名", notes = "线下活动报名", response = Response.class)
    @RequestMapping(value = "/signUp", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<String> signUp(

            @ApiParam(name = "activityVo", value = "报名时需要传递的用户id和活动id")
            @RequestBody ActivityVO activityVo){

        try {
            return offlineActivityEnterService.save(activityVo);

        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response<String>(Response.ERROR,"报名失败");
        }

    }

    @ApiOperation(value = "线上签到", notes = "线上签到", response = Response.class)
    @RequestMapping(value = "/Sign", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<String> Sign(
            @ApiParam(name = "activityVo", value = "签到时需要传递的用户id和活动id")
            @RequestBody ActivityVO activityVo){
        Response<String> resp = new Response<>(Response.SUCCESS,"签到成功");
        try {
            return offlineActivitySignService.save(activityVo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response<String>(Response.ERROR,"签到失败");
        }
    }


    @ApiOperation(value = "即将开始的活动", notes = "即将开始的活动" ,response = OfflineActivityDto.class)
    @RequestMapping(value = "/beginActivity", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<Page<OfflineActivityDto>> beginActivity(
            @ApiParam(name = "activityVo", value = "当前页数和显示多少条") @RequestBody ActivityVO activityVO){
        Response<Page<OfflineActivityDto>> res =new Response<Page<OfflineActivityDto>>();
        try {
            logger.info("--------------------查询即将开始的线下活动---------------------");
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", redisUtil.getUserId());
            paramMap.put("state",1);
            paramMap.put("pageSize", activityVO.getPageSize());
            paramMap.put("pageNo",activityVO.getCurrentPage());
            paramMap.put("outEnterEndDate",0);
            Page<OfflineActivityDto> publicClassPage = offlineActivityService.searchOfflineActivity(paramMap);
            res.setData(publicClassPage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        }catch (Exception e){
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }

    @ApiOperation(value = "历史活动", notes = "历史活动" ,response = OfflineActivityDto.class)
    @RequestMapping(value = "/endActivity", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<Page<OfflineActivityDto>> endActivity(
            @ApiParam(name = "activityVo", value = "当前页数和显示多少条")@RequestBody ActivityVO activityVO){
        Response<Page<OfflineActivityDto>> res =new Response<Page<OfflineActivityDto>>();
        try {
            logger.info("--------------------查询历史活动的线下活动---------------------");
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", redisUtil.getUserId());
            paramMap.put("state",1);
            paramMap.put("pageSize", activityVO.getPageSize());
            paramMap.put("pageNo",activityVO.getCurrentPage());
            paramMap.put("outEnterEndDate",1);
            Page<OfflineActivityDto> publicClassPage = offlineActivityService.searchOfflineActivity(paramMap);
            res.setData(publicClassPage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        }catch (Exception e){
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }



}
