package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.vo.ClassPlanDto;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.api.plan.vo.PlanAuthorityDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 班级计划 service
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
public interface ClassPlanService extends IService<ClassPlan> {

	/**
	 * 新增一条班级计划
	 * @param classPlan
	 * @param planCourseDtos
	 * @param planAuthorityDtos
	 * @param managerIds
	 * @param userIds
	 * @return
	 */
	public void insertClassPlan(ClassPlan classPlan, PlanCourseDto[] planCourseDtos, PlanAuthorityDto[] planAuthorityDtos, Long[] managerIds, Long userIds);
	
	/**
	 * 根据对象删除一条班级计划
	 * @param classPlan
	 * @return
	 */
	public boolean deleteClassPlan(ClassPlan classPlan);
	
	/**
	 * 批量删除班级计划
	 * @param plans
	 * @return
	 */
	public void deleteBatchClassPlan(List<ClassPlan> plans);
	
	/**
	 * 更新一条班级计划
	 * @param ClassPlan
	 * @return
	 */
	public boolean updateClassPlan(ClassPlan ClassPlan);
	
	/**
	 * 查询一条班级计划
	 * @param ClassPlan
	 * @return
	 */
	public ClassPlan findOnePlans(ClassPlan ClassPlan);
	
	/**
	 * 查询所有班级计划
	 * @return
	 */
	public List<ClassPlan> findAllClassPlan();
	
	/**
	 * 分页查询班级计划
	 * @param ClassPlan    查询对象条件
	 * @param pageNo     当前页码
	 * @param pageSize   每页展示数据最大条数
	 * @param pageSortFiled	   排序对象
	 * @param pageSortType	 排序规则          
	 * @return
	 */
	public Page<ClassPlan> findClassPlanByPage(ClassPlan ClassPlan, int pageNo, int pageSize, String pageSortFiled, String pageSortType);

	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	public ClassPlan findClassPlanDetailById(Long id);
	
	/**
	 * 分页查询班级计划
	 * @param ClassPlan    查询对象条件
	 * @param pageNo     当前页码
	 * @param pageSize   每页展示数据最大条数
	 * @param pageSortFiled	   排序对象
	 * @param pageSortType	 排序规则          
	 * @return
	 */
	PageInfo<ClassPlanVo> listByPageFM(Page<ClassPlanVo> page,String name ,String code,String tagId,
			  int pageNo, int pageSize);
	
	/**
	 * manage 批量启用
	 * @param ids
	 * @return
	 */
	Response<String> updateOrDeleForManage(String ids,String state);


	/**
	 * 查询班级计划
	 * @param paramMap
	 * @return
	 */
	Page<ClassPlanDto> searchClassPlan(Map<String,Object> paramMap );
	
	/**
	 * manage 新增班级计划
	 * @param classPlan
	 * @param planCourseDto
	 * @param planAuthorityDtos
	 * @param managerId
	 * @param userId
	 */
	Response<String> insertClassPlanFW(ClassPlan classPlan, PlanCourseDto[] planCourseDto, Long[] managerId, Long userId, 
	        ExpiredAlarm[] expiredAlarms,String isNot,PlanAuthority[] planAuthorityss, Long fileId);

	/**
	 * manage 修改班级计划
	 * @param classPlan
	 * @param planCourseDto
	 * @param planAuthorityDtos
	 * @param managerId
	 * @param userId
	 */
	Response<String> updateClassPlanFW(ClassPlan classPlan, PlanCourseDto[] planCourseDto, PlanAuthorityDto[] planAuthorityDtos, Long[] managerId, Long userId ,ExpiredAlarm[] expiredAlarms);

	/**
	 * Manage 查询单个班级计划
	 * @param classPlan
	 * @return
	 */
	Map<String,Object> findOnePlansFW(String id);
}
