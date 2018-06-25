package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.ClassPlanEnter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程评论 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-02-05
 */
@Mapper
public interface ClassPlanEnterMapper extends BaseMapper<ClassPlanEnter> {
    Integer getEnterCount(Map<String,Object> paramMap);

    List<ClassPlanEnter> findClassPlanEnterByMap(Map<String,Object> paramMap);



}
