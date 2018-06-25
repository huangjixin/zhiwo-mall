package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.plan.domain.ClassPlanEnter;
import com.fulan.api.plan.vo.ClassPlanEnterVo;
import com.fulan.api.security.domain.Account;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 班级计划 service
 * </p>
 *
 * @author Hedge
 * @since 2018-02-05
 */
public interface ClassPlanEnterService extends IService<ClassPlanEnter> {
    /**
     * 新增一条班级计划报名信息
     * @param classPlanEnter
     * @return
     */
    public Response<Boolean> insertClassPlanEnter(ClassPlanEnter classPlanEnter);
    
    /**
     * 新增一条班级计划报名信息（在线招募报名入口）
     * @param classPlanEnter
     * @return
     */
    public Response<Account> insertClassPlanEnterForEr(ClassPlanEnterVo classPlanEnterVo);
    
    
}
