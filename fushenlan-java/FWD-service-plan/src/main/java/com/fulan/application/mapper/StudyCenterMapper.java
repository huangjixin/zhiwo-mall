package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学习中心 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Mapper
public interface StudyCenterMapper extends BaseMapper<StudyPlanVo> {

	/**
	 * 根据用户ID获取我的计划
	 * @param planMap
	 * @return
	 */
	public List<StudyPlanVo> findMyClassPlanById(Page<StudyPlanVo> page,Map<String,Object> planMap);

	public List<StudyPlanVo> findMyPostPlanById(Page<StudyPlanVo> page,Map<String,Object> planMap);

	public List<StudyPlanVo> findMyCompPlanById(Page<StudyPlanVo> page,Map<String,Object> planMap);
	/**
	 * 我的积分
	 * @param id
	 * @return
	 */
	public SignIntegralVo getIntegral(@Param("id") Long id);
	
	/**
	 * 获取我的课程
	 * @param map
	 * @return
	 */
	public List<PlanCourseVo> getMyCourse(Map<String, Object> map);

	/**
	 * 获取线下活动收藏
	 * @param page
	 * @param activityVO
	 * @return
	 */
	public List<ActivityCollectVo> getActivityCollectByAccountId(Page<ActivityCollectVo> page,ActivityVO activityVO);

	/**
	 * 获取收藏的线下活动数量
	 * @param map
	 * @return
	 */
	public Integer queryActivityCollectCount(Map<String, Object> map);

	/**
	 * 获取收藏的公开课程
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public List<PublicClassCollectVo>  getPublicClassCollectByAccountId(Page<PublicClassCollectVo> page, Map<String,Object> paramMap);

	/**
	 * 根据ID删除收藏
	 * @param id
	 */
	public void deleteCollectById(@Param("id")Long id);

	/**
	 * 查询公开课程学习进度
	 * @param paramMap
	 * @return
	 */
	public Integer getLearningProgress(Map<String,Object> paramMap);

	/**
	 * 查询公开课下面所有课程ID
	 * @param paramMap
	 * @return
	 */
	public List<CourseVo> getCourseIdByPC(Map<String,Object> paramMap);

    /**
     * 历史记录
     * @param page
     * @param paramMap
     * @return
     */
	public List<HistoryVo> getHistoryRecord(Page<HistoryVo> page,Map<String,Object> paramMap);

    /**
	 * 删除历史记录
	 * @param id
	 */
	public void deleteHistoryById(@Param("id")Long id);

	/**
	 * 批量删除历史记录
	 * @param historyIds
	 */
	public void deleteHistoryByIds(@Param("historyIds")List<Long> historyIds);

	/**
	 * 批量删除收藏
	 * @param collectIds
	 */
	public void deleteCollectByIds(@Param("collectIds")List<Long> collectIds);

	/**
	 * 查询计划下面所有课程
	 * @param planId
	 * @return
	 */
	public List<CourseVo> getCourses(@Param("planId")Long planId);
}
