package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.plan.vo.PlanCoursePaperDto;
import com.fulan.api.plan.vo.PlanCourseVoQ;
import com.fulan.api.plan.vo.PlanDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 计划与课程中间表 Mapper 接口
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-19
 */
@Mapper
public interface PlanCourseMapper extends BaseMapper<PlanCourse> {

    /**
     * 获取课程大纲列表
     * @param paramMap
     * @return
     */
    public List<PlanCoursePaperDto> getCoursePaperListByPlan(Map<String,Object> paramMap);

    /**
     * 获取计划的阶段数
     * @param paramMap
     * @return
     */
    public Integer getPlanStageNum(Map<String,Object> paramMap);

    /**
     * 根据相关计划获取讲师列表
     * @param map
     * @return
     */
    public List<Long> getLecturerList(Map<String, Object> map);

    /**
     * 根据计划id获取计划名称和计划简介
     * @param map
     * @return
     */
    public PlanDetailDto getPlanDescById(Map<String, Object> map);
    
    /**
     * 根据courseId删除
     * @param cId
     * @return
     */
    public int deletePlanCourseId(@Param("cId")String cId);
    
    
    public List<CourseManageVo> selectByPlanId(@Param("id")String id);
    
    public List<CourseManageVo> selectByPlanOtherId(@Param("id")String id);
    
    


    /**
     * 根据planId查询
     * @param planId
     * @return
     */
    List<PlanCourseVoQ> selectPlanAndCourse(Long planId);
    //通过planid查找
    List<PlanCourseVoQ> selectPlanCourse(Long planId);
    
    List<PlanCourseDtoFMVo> seleByPlanIdLine(Long planId);



    /**
     * 获取课程大纲列表中的线下课程
     * @param paramMap
     * @return
     */
    public List<PlanCoursePaperDto> getOfflineCourseListByPlan(Map<String,Object> paramMap);
    
    public PlanCourse selectByPlanIdOff(String planId);

    /**
     * 根据计划信息获取第一个课程信息
     * @param paramMap
     * @return
     */
    public PlanCourse getFirstPlanCourse(Map<String,Object> paramMap);


    /**
     * 获取课程信息
     * @param paramMap
     * @return
     */
    public PlanCourse getPlanCourse(Map<String,Object> paramMap);
    /**
     * 查询最大的stage
     * @param id
     * @return
     */
    public String selectMaxByPlanId(@Param("id")String id);
}
