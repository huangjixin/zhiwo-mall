package com.fulan.api.elearning.vo.material;

import java.util.List;

import com.fulan.api.elearning.course.domain.Course;
import com.fulan.api.elearning.material.domain.Material;
import com.fulan.api.elearning.material.domain.MaterialShare;

import lombok.Data;
/**
 * 资料和课程 vo  该资料用于哪些课程中
 * @author kang
 *
 */
@Data
public class MaterialCourseVo extends Material{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Course> courses;
	private List<MaterialShare> materialShares;
}
