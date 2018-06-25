package com.fulan.application.service;

import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页 服务类
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
public interface IndexBaseService {

	/**
	 * 查询班级计划
	 * @param map
	 * @return
	 */
	public ClassPlanTopVo findClassPlanByAccountId(Map<String, Object> map);

	/**
	 * 查询班级计划（当前用户报名了的班级计划）
	 * @param map
	 * @return
	 */
	public ClassPlanTopVo findClassPlanByAccount(Map<String, Object> map);
	/**
	 * 查询职业发展（当前用户有权限的职业发展课程）
	 * @param map
	 * @return
	 */
	public PostDevelopmentVo findPostDevelopmentByAccount(Map<String, Object> map);

	/**
	 * 查询必修任务(当前用户有权限的职业发展课程）
	 * @param paramMap
	 * @return
	 */
	public CompulsoryPlanTopVo findCompulsoryCplanByAccount(Map<String, Object> paramMap);
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
	public CompulsoryPlanTopVo findCompulsoryCplanByAccountId(Map<String, Object> map);

	/**
	 * 资质考核
	 * @param appraiseDto
	 */
	public void appraise(AppraiseDto appraiseDto);

	/**
	 * 首页 获取即将过期的提醒
	 * @param id
	 * @return
	 */
	public List<RemindingExpireVo> getRemindingExpire(Long id);

	/**
	 * 学习完成-获取热度排名前三的公开课程
	 * @param id
	 * @return
	 */
	public List<HotPublicClassVo> getHotPublicClass( Long id);

	/**
	 * 获取用户报名情况
	 * @param accountId
	 * @return
	 */
	public List<ClassPlanVo> getEnterDetail( Long accountId);

}
