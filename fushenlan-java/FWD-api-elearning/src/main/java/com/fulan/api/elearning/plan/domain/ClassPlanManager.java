package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;

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
 * 班级计划负责人表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "ClassPlanManager", description = "班级计划负责人表")
@TableName("el_class_plan_manager")

public class ClassPlanManager implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "班级计划编号",name="planId")
	@TableField("plan_id")
	private Long planId;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;



}
