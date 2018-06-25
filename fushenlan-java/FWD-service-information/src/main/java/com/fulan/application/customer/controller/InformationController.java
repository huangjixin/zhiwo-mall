package com.fulan.application.customer.controller;

/**
 * Created by fsl on 2018/4/8.
 */

import com.fulan.api.information.vo.InformationContentVO;
import com.fulan.api.information.vo.InformationVO;
import com.fulan.api.information.vo.SignUpDTO;
import com.fulan.api.security.domain.Account;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.InformationService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页资讯
 */
@Api(tags = "InformationApi", description = "首页资讯")
@Controller
public class InformationController {

    private static final Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Autowired
    private InformationService informationService;


    @Autowired
    private RedisUtil redisUtil;


    /**
     * 查询页面资讯
     * @return
     */
    @ApiOperation(value = "资讯展示", notes = "资讯展示")
    @RequestMapping(value = "queryInformationList" ,method= RequestMethod.GET)
    @ResponseBody
    public Response<List<InformationVO>> queryInformationList(
            @ApiParam(name = "informationType", value = "资讯活动类型(必传)<br>0_公司动态<br>1_热门活动<br>2_政策公告<br>3_产品上线',")
            @RequestParam(required = true) Integer informationType
            ){

        Response<List<InformationVO>> resp = new Response<>(Response.SUCCESS,"成功");

        try {
            Response<List<InformationVO>> listResponse = informationService.queryInformationList(informationType);
            return listResponse;
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response<List<InformationVO>>(Response.ERROR,"失败");
        }
    }

    /**
     * 报名
     * @return
     */
    @ApiOperation(value = "报名", notes = "报名")
    @RequestMapping(value = "signUp" ,method= RequestMethod.POST)
    @ResponseBody
    public Response<String> signUp(
            @ApiParam(name = "signUpDTO", value = "活动id(必传)")
           @RequestBody SignUpDTO signUpDTO){

        Response<String> resp = new Response<>(Response.SUCCESS,"报名成功");

        try {
            return informationService.activitySignUp(signUpDTO.getActivityId());
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response<String>(Response.ERROR,"报名失败");
        }
    }
    @ApiOperation(value = "资讯详情", notes = "资讯详情")
    @RequestMapping(value = "queryDetailById" ,method= RequestMethod.GET)
    @ResponseBody
    public Response<InformationContentVO> queryDetailById(
            @ApiParam(name = "informationId", value = "资讯id(必传)")
            @RequestParam (required = true)Long informationId,
            @ApiParam(name = "infoNewsType", value = "资讯活动类型1资讯2活动(必传)")
            @RequestParam (required = true)Integer infoNewsType

    ){

        Response<String> resp = new Response<>(Response.SUCCESS,"查询成功");

        try {
           return informationService.queryDetailById(informationId,infoNewsType);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response<InformationContentVO>(Response.ERROR,"查询失败");
        }
    }





}
