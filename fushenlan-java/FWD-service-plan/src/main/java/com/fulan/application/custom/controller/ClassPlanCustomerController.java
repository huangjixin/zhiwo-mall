package com.fulan.application.custom.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.ClassPlanEnter;
import com.fulan.api.plan.vo.ClassPlanDto;
import com.fulan.api.plan.vo.ClassPlanEnterVo;
import com.fulan.api.plan.vo.PlanRequestDto;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.ClassPlanEnterService;
import com.fulan.application.service.ClassPlanService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 公开课前端控制器
 * </p>
 *
 * @author Hedge
 * @since 2018-02-02
 */
@Api(tags = "ClassPlanApi", description = "班级计划接口")
@RestController
@RequestMapping("/customer/classPlan")
public class ClassPlanCustomerController {

    private static final Logger logger = LoggerFactory.getLogger(ClassPlanCustomerController.class);

    @Autowired
    private ClassPlanService classPlanService;
    @Autowired
    private ClassPlanEnterService classPlanEnterService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     *
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "分页查询班级计划", notes = "分页查询班级计划", response = ClassPlanDto.class)
    @PostMapping(value = "/list")
    public Response<Page<ClassPlanDto>> listByPage(
            @ApiParam("userId:用户Id<br>pageNo:要跳转的页数<br>pageSize:每页条数")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<Page<ClassPlanDto>> res =new Response<Page<ClassPlanDto>>();
        try {
            logger.info("--------------------查询 班级计划---------------------");
            //班级计划分页查询
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("pageNo",planRequestDto.getPageNo());
            paramMap.put("pageSize",planRequestDto.getPageSize());
            paramMap.put("state",CommenConstant.VALUE_YES);
            paramMap.put("isTrainingNew",CommenConstant.VALUE_YES);
            paramMap.put("isRecruit",CommenConstant.VALUE_YES);
            Page<ClassPlanDto> classPlanDtoPagePage = classPlanService.searchClassPlan(paramMap);
            res.setData(classPlanDtoPagePage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }

    /**
     * 我要报名
     * @param classPlanEnter
     * @return
     */
    @ApiOperation(value = "我要报名", notes = "我要报名", response = Response.class)
    @PostMapping(value = "/enter")
    public Response<Boolean> enter(
            @ApiParam("planId:计划Id,required<br>")
            @RequestBody ClassPlanEnter classPlanEnter) {
        try {
            logger.info("-------------------"+"我要报名"+"----------------------");
            // 新增班级计划报名
            classPlanEnter.setUserId(Long.valueOf(redisUtil.getUserId().toString()));
            Response<Boolean> response= classPlanEnterService.insertClassPlanEnter(classPlanEnter);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }
    
    /**
     * 我要报名(招募入口)
     * @param classPlanEnter
     * @return
     */
    @ApiOperation(value = "我要报名", notes = "我要报名", response = Response.class)
    @PostMapping(value = "/enterForEr")
    public Response<Account> enterForEr(
            @ApiParam("planId:计划Id,required<br>")
            @RequestBody ClassPlanEnterVo classPlanEnterVo) {
        try {
            logger.info("-------------------"+"我要报名"+"----------------------");
            // 新增班级计划报名
            classPlanEnterVo.setUserId(Long.valueOf(redisUtil.getUserId().toString()));
            Response<Account> response= classPlanEnterService.insertClassPlanEnterForEr(classPlanEnterVo);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }
}
