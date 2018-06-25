package com.fulan.api.plan.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

@Data
public class OfflineActivityVOPC {
	
	private OfflineActivity offlineActivity;
	
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planCourseId;//学习计划课程id（修改的时候要传同时要传线下活动的id和）
	/*
	 *线下活动上课开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date startTime;
	/*
	 *线下活动上课结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	private Date endTime;
	private List<PlanAuthority> planAuthorityList;
	
	
	
}
