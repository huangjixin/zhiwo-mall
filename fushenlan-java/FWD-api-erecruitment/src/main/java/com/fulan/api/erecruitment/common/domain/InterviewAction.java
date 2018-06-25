package com.fulan.api.erecruitment.common.domain;

import java.io.Serializable;

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
 * 面试执行情况
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "InterviewAction", description = "面试执行情况")
@TableName("er_interview_action")

public class InterviewAction implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "人才id",name="personnelId")
	@TableField("personnel_id")
	private Long personnelId;

@ApiModelProperty(value = "流程模块id",name="flowActionId")
	@TableField("flow_action_id")
	private Long flowActionId;

@ApiModelProperty(value = "需要处理的模块",name="flowItemId")
	@TableField("flow_item_id")
	private Long flowItemId;

@ApiModelProperty(value = "开始时间",name="startTime")
	@TableField("start_time")
	private Date startTime;

@ApiModelProperty(value = "结束时间",name="endTime")
	@TableField("end_time")
	private Date endTime;

@ApiModelProperty(value = "面试官",name="processingPerson")
	@TableField("processing_person")
	private Long processingPerson;

@ApiModelProperty(value = "面试结果(1,通过，2不通过，3，更改计划)",name="processingStatus")
	@TableField("processing_status")
	private String processingStatus;

@ApiModelProperty(value = "面试描述",name="processingDesc")
	@TableField("processing_desc")
	private String processingDesc;

@ApiModelProperty(value = "",name="createTime")
	@TableField("create_time")
	private Date createTime;

@ApiModelProperty(value = "",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "",name="updateUser")
	@TableField("update_user")
	private Long updateUser;

@ApiModelProperty(value = "",name="updateTime")
	@TableField("update_time")
	private Date updateTime;



}
