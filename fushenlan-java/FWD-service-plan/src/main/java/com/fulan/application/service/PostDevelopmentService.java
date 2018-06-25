package com.fulan.application.service;


import java.util.List;
import java.util.Map;

import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PostDevelopmentVo;
import com.fulan.application.util.domain.Response;



public interface PostDevelopmentService {

    /**
     * 岗位新增
     */
	 public String insertPostDevelopment(PostDevelopmentVo PostDevelopment) ;


    /**
     * 查询一条岗位
     */
    public PostDevelopmentVo  getOnePostDevelopment(Long id);



    public PostDevelopmentVo  getPostDevelopment(Long id);


    /**
     * 插入岗位计划
     * @param postDevelopment
     * @param planCourseDtos
     * @param userId
     * @param expiredAlarms
     * @return
     */
	

	
	

	/**
	 * 根据职级id查询岗位发展计划
	 * @param 
	 * @return
	 */
	public Map<String, Object> findOneDevelopmentFW(String id);



    /**
     * 插入岗位计划
     * @param postDevelopment
     * @param planCourseDtos
     * @param userId
     * @param expiredAlarms
     * @return
     */
	
	public Response<String> insertDevelopmentOrupdate(PostDevelopment postDevelopment, PlanCourseDto[] planCourseDtos,
			ExpiredAlarm[] expiredAlarms, Long userId,String levelId,String levelName, Long fileId);


	


	

}
