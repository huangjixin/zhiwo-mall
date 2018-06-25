package com.fulan.api.plan.vo;

import java.io.Serializable;
import java.util.List;

import com.fulan.api.plan.domain.StudyPlan;

import lombok.Data;
/**
 * 
 * @author Q
 *
 */
@Data
public class StudyPlanCourse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudyPlan studyPlan;
	
	private List<PlanCourseVoQ> planCourse;
	
}
