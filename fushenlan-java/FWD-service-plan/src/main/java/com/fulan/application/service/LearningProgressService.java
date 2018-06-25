package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.LearningProgress;
import com.fulan.api.plan.domain.PlanComment;
import com.fulan.application.util.domain.Response;

import java.util.Map;

/**
 * <p>
 * 学习进度 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-02-01
 */
public interface LearningProgressService extends IService<LearningProgress> {

    /**
     * 新增一条学习记录
     * @param learningProgress
     * @return
     */
    public Response<Boolean> insertLearningProgress(LearningProgress learningProgress);

}
