package com.fulan.api.plan.vo;

import java.io.Serializable;
import java.util.List;

import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PublicClass;
import lombok.Data;
/**
 * 用于新增公开课程
 * @author chenzuyu
 *
 */
@Data
public class PublicClassInsertVo implements Serializable {


	private static final long serialVersionUID = 1L;
	private PublicClass publicClass;
	private List<PlanAuthority> planAuthorityList;
	private String[] planOrCourseId; //学习计划或基础课程ID
	private String type; //学习计划或基础课程
	private String studyPlanName; //学习计划名称
	private String studyPlanDescription; //学习计划描述
}
