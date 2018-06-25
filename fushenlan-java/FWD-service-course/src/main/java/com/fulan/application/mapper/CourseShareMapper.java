package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.course.domain.CourseShare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseShareMapper extends BaseMapper<CourseShare> {

    /**
     * 根据课程Id获取课程公开信息
     * @param courseId
     * @return
     */
    CourseShare findByCourseId(@Param("courseId") Long courseId);
}
