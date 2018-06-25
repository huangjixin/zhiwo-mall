package com.fulan.api.material.vo;

import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import lombok.Data;

import java.util.List;
import java.util.Set;
/**
 * 资料和课程 vo  该资料用于哪些课程/
 * @author kang
 *
 */
@Data
public class MaterialCourseVo extends Material{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<String> courses; //courses 课程名称，
	private Set<String> classPlan; // 班级计划
	private Set<String> postDevelopment; //岗位发展
	private Set<String> compulsoryCplan; // 必修任务
	private Set<String> studyPlan; // 学习计划
 	private List<MaterialShare> materialShares;
}
