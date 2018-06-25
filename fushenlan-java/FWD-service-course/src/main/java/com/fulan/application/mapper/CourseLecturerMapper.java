package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.course.domain.CourseLecturer;

@Mapper
public interface CourseLecturerMapper extends BaseMapper<CourseLecturer> {

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
