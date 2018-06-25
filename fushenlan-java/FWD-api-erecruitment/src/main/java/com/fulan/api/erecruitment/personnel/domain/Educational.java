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
 * 教育经历
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Educational", description = "教育经历")
@TableName("er_educational")

public class Educational implements Serializable {

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

@ApiModelProperty(value = "学历",name="education")
	private String education;

@ApiModelProperty(value = "学校",name="school")
	private String school;

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
