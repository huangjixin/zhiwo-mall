package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.PlanCollect;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
public interface PlanCollectService extends IService<PlanCollect> {


    /**
     * 新增一条收藏
     * @param planCollect
     * @return
     */
    public Response<PlanCollect> insertPlanCollect(PlanCollect planCollect);

    /**
     * 删除一条收藏
     * @param planCollectId
     * @return
     */
    public Response<Boolean> deletePlanCollect(Long planCollectId);
}
