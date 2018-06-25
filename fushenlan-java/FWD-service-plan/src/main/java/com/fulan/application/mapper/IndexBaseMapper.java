package com.fulan.application.mapper;

import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 必修任务表 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Mapper
public interface IndexBaseMapper{
    /**
     * 查询班级计划
     * @param map
     * @return
     */
	public ClassPlanVo findClassPlanByAccountId(Map<String, Object> map);

    /**
     * 查询岗位计划
     * @param map
     * @return
     */
	public PostDevelopmentVo findPostDevelopmentByAccountId(Map<String, Object> map);

    /**
     * 查询必修任务
     * @param map
     * @return
     */
	public List<CompulsoryPlanVo> findCompulsoryCplanByAccountId(Map<String, Object> map);

    /**
     * 查询 计划学习进度 (班级计划 )
     * @param map
     * @return
     */
	public String getLearningTime(Map<String, Object> map);


    /**
     * 首页 获取即将过期的提醒
     * @param id
     * @return
     */
	public List<RemindingExpireVo> getRemindingExpire(@Param("id") Long id);

    /**
     * 学习完成-获取热度排名前三的公开课程
     * @param id
     * @return
     */
	public List<HotPublicClassVo> getHotPublicClass(@Param("id") Long id);


    /**
     * 获取每个阶段课程学习状态
     * @param map
     * @return
     */
	public Integer getCourseStatus(Map<String,Object> map);

    /**
     * 获取考核状态
     * @param map
     * @return
     */
	public Integer getPaperStatus(Map<String,Object> map);

    /**
     * 获取试卷阅卷状态
     * @param map
     * @return
     */
	public Integer getPaperState(Map<String,Object> map);

    /***
     * 查询用户测试次数
     * @param map
     * @return
     */
	public Integer getExamNum(Map<String,Object> map);

    /**
     * 根据用户ID查询班级计划ID
     * @param map
     * @return
     */
	public List<Long> getPlanIdByAccountId(Map<String,Object> map);

    /**
     * 根据职级获取岗位计划ID
     * @param map
     * @return
     */
	public Long getPostIdByPostType(Map<String,Object> map);

    /**
     * 获取用户报名情况
     * @param accountId
     * @return
     */
	public List<ClassPlanVo> getEnterDetail(@Param("accountId") Long accountId);

    /**
     * 获取计划的每个阶段的课程大纲
     * @param map
     * @return
     */
	public List<CourseVo> getCoursePaperListByPlan(Map<String,Object> map);

    /**
     * 该阶段有木有开始学习0：未开始 1：开始
     * @param map
     * @return
     */
    public Integer getStartFlag(Map<String,Object> map);

    /**
     * 查询班级计划
     * @param planId
     * @return
     */
    public ClassPlanVo findClassPlan(@Param("planId") Long planId);

    /**
     * 查询必修任务
     * @param map
     * @return
     */
    public List<CompulsoryPlanVo> findCompulsoryPlan(Map<String,Object> map);


    /**
     * 查询岗位发展计划
     * @param map
     * @return
     */
    public PostDevelopmentVo findPostDevelopment(Map<String,Object> map);

}
