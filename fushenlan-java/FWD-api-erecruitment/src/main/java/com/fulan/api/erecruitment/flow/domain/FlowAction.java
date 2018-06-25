package com.fulan.api.erecruitment.flow.domain;

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
 * 流程执行
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "FlowAction", description = "流程执行")
@TableName("er_flow_action")

public class FlowAction implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "流程id",name="flowId")
	@TableField("flow_id")
	private Long flowId;

@ApiModelProperty(value = "流程模块id",name="flowItemId")
	@TableField("flow_item_id")
	private Long flowItemId;

@ApiModelProperty(value = "步骤",name="step")
	private Integer step;

@ApiModelProperty(value = "负责人",name="processingRole")
	@TableField("processing_role")
	private Long processingRole;

@ApiModelProperty(value = "条件",name="condition")
	private String condition;

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
