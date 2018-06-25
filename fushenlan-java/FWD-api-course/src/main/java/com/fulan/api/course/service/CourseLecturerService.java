package com.fulan.api.course.service;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.course.domain.CourseLecturer;
/**
 * <p>
 * 基础课程 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@FeignClient(name="course"/*,configuration=MyConfiguration.class*/)
public interface CourseLecturerService {
	
	 /**
     * 根据基础课程id或讲师id查询
     * @param courseLecturer
     * @return
     */
	 @PostMapping("/manage/courseLecturer/findCourseLecturer")
    List<CourseLecturer> findCourseLecturer(@RequestParam(value="courseId",required=false) Long courseId,@RequestParam(value="lecturerId",required=false) Long lecturerId);
    
    /**
     * 根据基础课程id删除
     * @param courseId
     * @return
     */
	 @PostMapping("/manage/courseLecturer/deleteCourseLecturerByCourseId")
    int deleteCourseLecturerByCourseId(Long courseId);
    
    /**
     * 批量插入
     * @param courseLecturers
     * @return
     */
	 @PostMapping("/manage/courseLecturer/inserBylist")
    int inserBylist(List<CourseLecturer> courseLecturers);
    
}
