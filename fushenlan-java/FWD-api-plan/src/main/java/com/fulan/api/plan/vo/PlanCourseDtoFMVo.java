package com.fulan.api.plan.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.course.domain.Course;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * manage 
 * @author Administrator
 *
 */

@Datapublic class PlanCourseDtoFMVo {
	
	@IdAnnon
	private Long id;

	@TableField("plan_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long planId;

	@TableField("course_type")
	private Integer courseType;

	@TableField("associate_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long associateId;

	@TableField("associate_type")
	private Integer associateType;

	@TableField("is_compulsory")
	private Integer isCompulsory;

	private Integer stage;

	private Integer seq;

	@TableField("start_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date startTime;

	@TableField("end_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date endTime;

	@TableField("sign_address")
	private String signAddress;

	@TableField("create_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@TableField("gmt_create")
	private Date gmtCreate;

	@TableField("modify_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	@TableField("gmt_modified")
	private Date gmtModified;
	
	private String name;

}
