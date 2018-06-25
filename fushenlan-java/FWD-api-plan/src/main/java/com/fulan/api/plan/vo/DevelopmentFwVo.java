package com.fulan.api.plan.vo;

import java.util.List;

import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.domain.PostDevelopment;

import lombok.Data;
/**
 * manage
 * @author Administrator
 *
 */
@Data
public class DevelopmentFwVo {
	
	/*
	 * 阶段
	 */
	private Integer stage;
	
	private String levelId;
	
	private String levelName;
	
	private PostDevelopment postDevelopment;

	private List<PlanCourseDto> planCourseDto;
	
	private List<PlanCourseDtoFMVo> planCourseDtoFMVo;
	
	private List<PlanAuthorityDto> planAuthorityDto;
	
	private Long[] managerIds;
	
	/**
	 * 修改时判断名称是否修改
	 */
	private String isNot;
	
	private List<ExpiredAlarm> expiredAlarmList;
}
