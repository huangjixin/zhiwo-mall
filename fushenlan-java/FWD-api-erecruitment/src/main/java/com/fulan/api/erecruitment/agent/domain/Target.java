package com.fulan.api.erecruitment.agent.domain;

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
 * 代理人目标
 * </p>
 *
 * @author fulan123
 * @since 2018-01-22
 */
@Data
@Api(tags = "Target", description = "代理人目标")
@TableName("er_target")

public class Target implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键", name = "id")
	private Long id;

	@ApiModelProperty(value = "代理人id", name = "accountId")
	@TableField("account_id")
	private Long accountId;

	@ApiModelProperty(value = "目标时间", name = "targetTime")
	@TableField("target_time")
	private String targetTime;

	@ApiModelProperty(value = "目标值", name = "targetValue")
	@TableField("target_value")
	private Integer targetValue;

	@ApiModelProperty(value = "create_time", name = "createTime")
	private Date createTime;

	@ApiModelProperty("create_user")
	private Long createUser;

	@ApiModelProperty(value = "update_time", name = "updateTime")
	private Date updateTime;

	@ApiModelProperty(value = "update_user", name = "updateUser")
	private Long updateUser;

}
