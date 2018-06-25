package com.fulan.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.application.mapper.CourseLecturerMapper;
import com.fulan.application.service.CourseLecturerService;

@Service
@Transactional
public class CourseLecturerServiceImpl extends ServiceImpl<CourseLecturerMapper,CourseLecturer> implements CourseLecturerService{
    @Autowired
    private CourseLecturerMapper CourseLecturerMapper;

	@Override
	public List<CourseLecturer> findCourseLecturer(@RequestBody CourseLecturer courseLecturer) {
		return CourseLecturerMapper.findCourseLecturer(courseLecturer);
	}

	@Override
	public int deleteCourseLecturerByCourseId(Long courseId) {
		return CourseLecturerMapper.deleteCourseLecturerByCourseId(courseId);
	}

	@Override
	public int inserBylist(List<CourseLecturer> courseLecturers) {
		return CourseLecturerMapper.inserBylist(courseLecturers);
	}


}
