package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.LearningProgress;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 计划与课程中间表 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-02-01
 */
@Mapper
public interface LearningProgressMapper extends BaseMapper<LearningProgress> {

    /**
     * 获取用户最新学习该计划的记录
     * @param paramMap
     * @return
     */
    LearningProgress getLearningProgress(Map<String,Object> paramMap);
}
