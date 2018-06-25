package com.fulan.application.service;

import java.util.List;

import com.fulan.api.plan.domain.StudyPlanCopy;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.application.util.page.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.StudyPlan;

/**
 * <p>
 * 学习计划 服务类
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
public interface StudyPlanService extends IService<StudyPlan>{

	/**
	 * 新增一条学习计划
	 * @param studyPlan
	 * @return
	 */
	void insertElStudyPlan(StudyPlanCopy studyPlan);
	
	/**
	 * 分页查询学习计划
	 * @param code
	 * @param name
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<StudyPlan> findPlanByPage(String code, String name, String tagId, int pageNo, int pageSize);

	/**
	 * 根据ID批量删除学习计划
	 * @param planIds
	 */
	public void deletePlanBatchByIds(Long[] planIds);
	

	/**
	 * 查询学习计划详情
	 * @param id
	 * @return
	 */
	public StudyPlan findPlanDetailById(Long id);

	StudyPlanCopy selectPlanAndCourse(Long id);

	public List<StudyPlan> studyPlanList(StudyPlan studyPlan);
	
	
	/**
	 * 查询学习计划详情
	 * @param id
	 * @return
	 */
	public StudyPlan selectPlanById(Long id);
	
	/**
	 * 根据id修改状态
	 * @param studyPlan
	 */
	public void updateStudyPlan(StudyPlan studyPlan);
	
	
	/**
	 * 批量删除学习计划
	 * 
	 * Q
	 */
	public void deleteBetchPlan(List<Long> ids);
	
	/**
	 * 根据搜索条件查找
	 * @param studyPlan
	 * @return
	 * Q
	 */
	public List<StudyPlan> serachFind(StudyPlan studyPlan);
	
	/**
	 * 查找是否别关联
	 * @param id
	 * @return
	 * Q
	 */
	public Integer findAssociated(Long id);
	
	public List<StudyPlan> planList(String keyWord,String code,String tagId,String uploadTimeBegin,String uploadTimeEnd);
	

	public PageInfo<StudyPlanVvo> listByPage(Page<StudyPlanVvo> page,String keyWord, int pageNo, int pageSize);
		
	public PageInfo<StudyPlanVvo> listPlan(Page<StudyPlanVvo> page,String name,String code,String tagId, int pageNo, int pageSize);







}
