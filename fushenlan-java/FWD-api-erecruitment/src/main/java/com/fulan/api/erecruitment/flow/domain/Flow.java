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
 * 面试流程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Flow", description = "面试流程")
@TableName("er_flow")

public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "流程说明",name="flowDesc")
	@TableField("flow_desc")
	private String flowDesc;

@ApiModelProperty(value = "流程名称",name="flowName")
	@TableField("flow_name")
	private String flowName;

@ApiModelProperty(value = "所属分公司",name="orgId")
	@TableField("org_id")
	private Long orgId;

@ApiModelProperty(value = "创建时间",name="createTime")
	@TableField("create_time")
	private Date createTime;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "更新时间",name="updateTime")
	@TableField("update_time")
	private Date updateTime;

@ApiModelProperty(value = "更新人",name="updateUser")
	@TableField("update_user")
	private Long updateUser;



}
