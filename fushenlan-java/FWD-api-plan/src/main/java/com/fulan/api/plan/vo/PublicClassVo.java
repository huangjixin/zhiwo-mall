package com.fulan.api.plan.vo;

import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.domain.StudyPlan;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * 后台 公共课 Vo 包含学习计划信息  以及计划中包含的课程/试卷信息 、以及公共课适用人群
 *  
 * @author kang
 *
 */
@Data
public class PublicClassVo extends PublicClass implements Serializable {

	private static final long serialVersionUID = 1L;
	private StudyPlan studyPlan; //学习计划
	private List<CourseVo> course;
	
}
