package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.application.mapper.CourseShareMapper;
import com.fulan.application.service.CourseShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 基础课程分享服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-22
 */
@Service
@Transactional
public class CourseShareServiceImpl extends ServiceImpl<CourseShareMapper,CourseShare> implements CourseShareService {

    @Autowired
    private CourseShareMapper courseShareMapper;

    @Override
    public CourseShare findByCourseId(Long courseId) {
        return courseShareMapper.findByCourseId(courseId);
    }
}
