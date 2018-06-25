package com.fulan.api.course.vo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 计划与课程中间表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanCourse", description = "计划与课程中间表")
@TableName("el_plan_course")
public class CourseAndPlanVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6637713763663812755L;

	@ApiModelProperty(value = "计划课程关联id", name = "id")
	@IdAnnon
	private Long id;

	@ApiModelProperty(value = "计划id", name = "planId")
	@TableField("plan_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long planId;

	@ApiModelProperty(value = "计划类型-公开课/学习计划/班级计划/岗位发展/必修任务", name = "courseType")
	@TableField("course_type")
	private Integer courseType;

	@ApiModelProperty(value = "基础课程编号", name = "associateId")
	@TableField("associate_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long associateId;

	@ApiModelProperty(value = "关联类别-试卷/线上课程/线下课程", name = "associateType")
	@TableField("associate_type")
	private Integer associateType;

	@ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
	@TableField("is_compulsory")
	private Integer isCompulsory;

	@ApiModelProperty(value = "所属阶段", name = "stage")
	private Integer stage;

	@ApiModelProperty(value = "排序", name = "seq")
	private Integer seq;

	@ApiModelProperty(value = "开始时间", name = "startTime")
	@TableField("start_time")
	private Date startTime;

	@ApiModelProperty(value = "截止时间", name = "endTime")
	@TableField("end_time")
	private Date endTime;

	@ApiModelProperty(value = "签到地点", name = "signAddress")
	@TableField("sign_address")
	private String signAddress;

	@ApiModelProperty(value = "创建人", name = "createUser")
	@TableField("create_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@ApiModelProperty(value = "创建时间", name = "gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

	@ApiModelProperty(value = "修改人", name = "modifyUser")
	@TableField("modify_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	@ApiModelProperty(value = "修改时间", name = "gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;




}
