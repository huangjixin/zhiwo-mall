package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.vo.PlanDetailDto;
import com.fulan.api.plan.vo.PublicClassVo;
import com.fulan.api.plan.vo.PublicCourseDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公开课 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
public interface PublicClassService {
    String handPubClass(PublicClass pubclass,Map<String,String[]> param,List<PlanAuthority> planAuthorityList
    		,String studyPlanName,String studyPlanDescription, Long fileId);
    PageInfo<PublicCourseDto> manageListByPage(Page<PublicCourseDto> page,Map<String,Object> map);
    List<Course> courseList(Map<String,Object> map);
    PublicClassVo publicClassVoInFo(String id);

    /**
     * 查询公开课
     * @param paramMap
     * @return
     */
    Page<PublicCourseDto> searchPublicClass(Map<String,Object> paramMap );

    /**
     * 获取计划详情，资料列表、讲师列表,相关课程
     * @param planId
     * @param courseId
     * @param planType
     * @return
     */
    public PlanDetailDto getPlanDetailDto(Long planId,Long courseId,Integer planType,Long userId);
    
    
	PageInfo<PublicCourseDto> managePublicListByPage(Page<PublicCourseDto> page, Map<String, Object> map);
	
	
	/**
	 * 批量上架或下架公共课
	 * @param state 1：上架/2：下架
	 * @param publicClassIds：公共课id
	 * @return
	 */
	Response<Boolean> batchShelves(String[] publicClassIds, String state);
	
	String updatePublicClass(PublicClass pubclass);
	
	PublicClass selectOne(String id);
}
