package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.CourseCollect;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程/计划收藏表 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Mapper
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {

    Long selectCourseById(Long userId);

    void deleteCollect(Long courseCollectId);
}
