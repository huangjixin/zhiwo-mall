package com.fulan.application.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.fulan.application.service.PostDevelopmentService;
import com.fulan.application.util.domain.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PlanAuthorityDto;
import com.fulan.api.plan.vo.PostDevelopmentVo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 岗位发展  控制器
 * 
 *
 */
@RestController
@RequestMapping("/manage/postDevelopment")
@Api(tags = "PostDevelopment", description = "岗位发展管理接口")
public class PostDevelopmentController {
    private Logger logger = LoggerFactory.getLogger(PostDevelopmentController.class);
    @Autowired
    private PostDevelopmentService postDevelopmentService;
    


    //编辑岗位路线(新增)
    @ApiOperation(value = "岗位编辑",notes = "新增一个岗位",response =Response.class )
    @RequestMapping("/insert")

       public String insert(@RequestBody PostDevelopmentVo PostDevelopment){
            logger.info("---------------------新增岗位---------------------");
            return   postDevelopmentService.insertPostDevelopment(PostDevelopment);
    }
    
    
    /**
     * 插入岗位计划
     * @param classPlanFwVo
     * @param userId
     * @return
     */

	@PostMapping("insertDevelopmentOrupdate")
	public Response<String> insertDevelopmentOrupdate(@RequestBody DevelopmentFwVo developmentFwVo,
			@RequestParam("userId") Long userId,
			@RequestParam (name="fileId",required = false)Long fileId){
		try { 
			PostDevelopment postDevelopment = developmentFwVo.getPostDevelopment();
			PlanCourseDto[] planCourseDtos = new PlanCourseDto[]{};
			ExpiredAlarm[] expiredAlarms = new ExpiredAlarm[]{};
			String levelId=developmentFwVo.getLevelId();
			if(null != developmentFwVo.getPlanCourseDto()){
				planCourseDtos = developmentFwVo.getPlanCourseDto().toArray(planCourseDtos);
			}
			if(null != developmentFwVo.getExpiredAlarmList()){
				expiredAlarms = developmentFwVo.getExpiredAlarmList().toArray(expiredAlarms);
			}
			String levelName=developmentFwVo.getLevelName();
			return postDevelopmentService.insertDevelopmentOrupdate(postDevelopment,planCourseDtos,
			        expiredAlarms,userId,levelId,levelName, fileId);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
	    }
	}


    @ApiOperation(value = "岗位编辑",notes = "查看一个岗位")
    @RequestMapping("/getOnePostDevelopment")
    @ResponseBody
    public PostDevelopmentVo getOnePostDevelopment(@RequestParam Long id){
        return   postDevelopmentService.getOnePostDevelopment(id);
    }
    
   
    
   
   
    
    @PostMapping("/findOneDevelopmentFW")
    @ResponseBody
    public Map<String, Object> findOneDevelopmentFW(@RequestParam("id") String id){
       return postDevelopmentService.findOneDevelopmentFW(id);
    }
    
    
    
    
    
}
