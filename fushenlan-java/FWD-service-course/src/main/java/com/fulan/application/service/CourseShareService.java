package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.domain.CourseShare;

/**
 * <p>
 * 基础课程分享服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-22
 */
public interface CourseShareService extends IService<CourseShare> {

    /**
     * 根据课程Id获取课程公开信息
     * @param courseId
     * @return
     */
    CourseShare findByCourseId(Long courseId);
}
