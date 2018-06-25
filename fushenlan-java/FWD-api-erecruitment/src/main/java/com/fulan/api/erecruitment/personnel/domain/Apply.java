package com.fulan.api.erecruitment.personnel.domain;

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
 * 计划申请
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Apply", description = "计划申请")
@TableName("er_apply")

public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "人才id",name="personnelId")
	@TableField("personnel_id")
	private Long personnelId;

@ApiModelProperty(value = "人才计划",name="talentPlan")
	@TableField("talent_plan")
	private String talentPlan;
@ApiModelProperty(value = "",name="refereeName")
    @TableField("referee_no")
    private String refereeNo;
@ApiModelProperty(value = "推荐人姓名",name="refereeName")
	@TableField("referee_name")
	private String refereeName;

@ApiModelProperty(value = "推荐人证件号码",name="occupation")
	private String occupation;

@ApiModelProperty(value = "推荐人开始日期",name="refereeStartTime")
	@TableField("referee_start_time")
	private String refereeStartTime;

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
