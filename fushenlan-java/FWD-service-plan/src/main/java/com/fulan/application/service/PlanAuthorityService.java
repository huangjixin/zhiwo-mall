package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.PlanAuthority;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程权限表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface PlanAuthorityService extends IService<PlanAuthority> {

    /**
     * 新增一跳课程权限
     * @param planAuthority
     */
    public void insertPlanAuthority(PlanAuthority planAuthority);

    /**
     * 查看用户是否有该计划的权限
     * @param paramMap
     * @return
     */
    Integer hasAuthority(Map<String,Object> paramMap);

    /**
     * 根据课程Id查询所有关联的associateId
     * @param courseId
     * @return
     */
	public List<PlanAuthority> selectByCourseId(String courseId);
}
