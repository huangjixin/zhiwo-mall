package com.fulan.api.elearning.vo.course;

import java.util.List;

import com.fulan.api.elearning.course.domain.Course;
import com.fulan.api.elearning.course.domain.CourseShare;
import com.fulan.api.elearning.material.domain.Material;

import lombok.Data;
@Data
public class CourseVo extends Course{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Material> materials;
	List<CourseShare> courseShares;
		
}
