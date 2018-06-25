package com.fulan.api.elearning.plan.domain;

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
 * 线下活动签到表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "OfflineActivitySign", description = "线下活动签到表")
@TableName("el_offline_activity_sign")

public class OfflineActivitySign implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "线下活动编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "线下活动时间编号",name="courseTimeId")
	@TableField("course_time_id")
	private Long courseTimeId;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;

@ApiModelProperty(value = "用户名称",name="userName")
	@TableField("user_name")
	private String userName;

@ApiModelProperty(value = "是否报名",name="isEnter")
	@TableField("is_enter")
	private Integer isEnter;

@ApiModelProperty(value = "签到时间",name="signTime")
	@TableField("sign_time")
	private Date signTime;

@ApiModelProperty(value = "报名时间",name="enterTime")
	@TableField("enter_time")
	private Date enterTime;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
