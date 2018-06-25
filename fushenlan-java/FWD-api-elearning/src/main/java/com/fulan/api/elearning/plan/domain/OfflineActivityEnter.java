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
 * 线下活动报名表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "OfflineActivityEnter", description = "线下活动报名表")
@TableName("el_offline_activity_enter")

public class OfflineActivityEnter implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "线下活动编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;

@ApiModelProperty(value = "用户名称",name="userName")
	@TableField("user_name")
	private String userName;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
