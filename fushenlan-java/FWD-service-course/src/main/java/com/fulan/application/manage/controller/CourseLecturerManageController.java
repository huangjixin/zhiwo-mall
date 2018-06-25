package com.fulan.application.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.application.service.CourseLecturerService;
import com.fulan.application.util.domain.Response;

/**
 *
 */
@RestController
@RequestMapping("/manage/courseLecturer")
public class CourseLecturerManageController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseLecturerManageController.class);

	@Autowired
	private CourseLecturerService courseLecturerService;
	
	@RequestMapping(value="/findCourseLecturer",method=RequestMethod.POST)
	public @ResponseBody List<CourseLecturer> findCourseLecturer(@RequestParam(value="courseId",required=false) Long courseId,@RequestParam(value="lecturerId",required=false) Long lecturerId){
		CourseLecturer courseLecturer = new CourseLecturer();
		if(courseId!=null) {
			courseLecturer.setCourseId(courseId);
		}
		if(lecturerId!=null) {
			courseLecturer.setLecturerId(lecturerId);
		}
		List<CourseLecturer> courseLecturerList = courseLecturerService.findCourseLecturer(courseLecturer );
		return courseLecturerList;
	}
	
	@RequestMapping(value="deleteCourseLecturerByCourseId",method=RequestMethod.POST)
	public @ResponseBody int deleteCourseLecturerByCourseId(Long courseId){
		int resultData = courseLecturerService.deleteCourseLecturerByCourseId(courseId);
		return resultData;
	}
	
	@RequestMapping(value="inserBylist",method=RequestMethod.POST)
	public @ResponseBody int inserBylist(List<CourseLecturer> courseLecturers){
		int resultData = courseLecturerService.inserBylist(courseLecturers);
		return resultData;
	}
	
}
