package com.fulan.api.course.vo;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.material.domain.Material;
import lombok.Data;

import java.util.List;

@Data
public class CourseVo extends Course{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Material> materials;
	List<CourseShare> courseShares;
		
}
