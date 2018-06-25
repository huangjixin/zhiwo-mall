package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.plan.vo.PlanCoursePaperDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 计划与课程中间表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface PlanCourseService extends IService<PlanCourse> {

    public void insertPlanCourse(PlanCourse planCourse) ;


    /**
     * 分阶段获取课程大纲列表
     * @param paramMap
     * @retur
     */
    public Map<String,List<PlanCoursePaperDto>> getCoursePaperListByPlan(Map<String,Object> paramMap);
    
    /**
     * 查询线下课程
     * @param planId
     * @return
     */
    public List<PlanCourseDtoFMVo> seleByPlanIdLine(Long planId);
    
    public List<CourseManageVo> selectByPlanId(String id);
    
    public List<CourseManageVo> selectByPlanOtherId(String id);

    /**
     * 根据计划信息获取第一个课程信息
     * @param planId
     * @param planType
     * @return
     */
    public PlanCourse getFirstPlanCourse(Long planId,Integer planType);
    
    public PlanCourse selectByPlanIdOff(String planId);
    


}
