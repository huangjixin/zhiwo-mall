package com.fulan.api.erecruitment.personnel.domain;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 工作经历
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "WorkExperience", description = "工作经历")
@TableName("er_work_experience")

public class WorkExperience implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "人才id",name="personnelId")
	@TableField("personnel_id")
	private Long personnelId;

@ApiModelProperty(value = "开始时间",name="startTime")
	@TableField("start_time")
	private Date startTime;

@ApiModelProperty(value = "结束时间",name="endTime")
	@TableField("end_time")
	private Date endTime;

@ApiModelProperty(value = "工作年限",name="workTime")
	@TableField("work_time")
	private BigDecimal workTime;

@ApiModelProperty(value = "职业",name="occupation")
	private String occupation;

@ApiModelProperty(value = "职位",name="post")
	private String post;

@ApiModelProperty(value = "公司",name="company")
	private String company;

@ApiModelProperty(value = "年收入",name="annualIncome")
	@TableField("annual_income")
	private Long annualIncome;

@ApiModelProperty(value = "",name="createTime")
	@TableField("create_time")
	private Date createTime;

@ApiModelProperty(value = "",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "",name="updateTime")
	@TableField("update_time")
	private Date updateTime;

@ApiModelProperty(value = "",name="updateUser")
	@TableField("update_user")
	private Long updateUser;



}
