package com.fulan.api.plan.vo;

import java.util.List;

import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanCourse;

import lombok.Data;

@Data
public class CompulsoryCplanManageVo {
    
	private CompulsoryCplan compulsoryCplan;
	
	private List<ExpiredAlarm> expiredAlarmList;
	
	private List<PlanCourse> planCourseList;
	
	private List<PlanAuthority> planAuthorityList;
}
