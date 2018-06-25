package com.fulan.application.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 我的计划 服务类
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
public interface StudyCenterService {

	/**
	 * 根据用户ID获取我的计划
	 * @param pageMap
	 * @return
	 */
	public Page<StudyPlanVo> findMyClassPlanById(Map<String,Object> pageMap);

	public Page<StudyPlanVo> findMyPostPlanById(Map<String,Object> planMap);

	public Page<StudyPlanVo> findMyCompPlanById(Map<String,Object> planMap);
	
	/**
	 * 我的积分
	 * @param id
	 * @return
	 */
	public SignIntegralVo getIntegral(Long id);
	
	/**
	 * 获取我的课程
	 * @param map
	 * @return
	 */
	public Object getMyCourse(Map<String, Object> map);

	/**
	 * 获取线下活动收藏
	 * @param activityVO
	 * @return
	 */
	public Page<ActivityCollectVo> getActivityCollectByAccountId(ActivityVO activityVO);

	/**
	 * 获取收藏的公开课程
	 * @param paramMap
	 * @return
	 */
	public Page<PublicClassCollectVo>  getPublicClassCollectByAccountId( Map<String,Object> paramMap);

	/**
	 * 根据ID删除收藏
	 * @param id
	 */
	public void deleteCollectById(Long id);

	/**
	 * 历史记录
	 * @param paramMap
	 * @return
	 */
	public Page<HistoryVo> getHistoryRecord(Map<String,Object> paramMap);

	/**
	 * 删除历史记录
	 * @param ids
	 */
	public void deleteHistoryByIds(List<Long> ids);

	/**
	 * 根据ID删除收藏
	 * @param ids
	 */
	public void deleteCollectByIds(List<Long> ids);

	/**
	 * 签到领取积分
	 * @return
	 */
	public SignIntegralVo sign();
	
}
