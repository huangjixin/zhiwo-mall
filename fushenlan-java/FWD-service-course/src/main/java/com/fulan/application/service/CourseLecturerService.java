package com.fulan.application.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.domain.CourseLecturer;

/**
 * <p>
 * 基础课程服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-22
 */
public interface CourseLecturerService extends IService<CourseLecturer> {

	 /**
     * 根据基础课程id或讲师id查询
     * @param courseLecturer
     * @return
     */
    List<CourseLecturer> findCourseLecturer(@RequestBody CourseLecturer courseLecturer);
    
    /**
     * 根据基础课程id删除
     * @param courseId
     * @return
     */
    int deleteCourseLecturerByCourseId(Long courseId);
    
    /**
     * 批量插入
     * @param courseLecturers
     * @return
     */
    int inserBylist(List<CourseLecturer> courseLecturers);
}
