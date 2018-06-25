package com.fulan.api.security.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@TableName("target")

public class Target implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "主键", name = "id")
	private Long id;

	@ApiModelProperty(value = "代理人id", name = "accountId")
	@TableField("account_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long accountId;

	@ApiModelProperty(value = "目标年份", name = "targetYear")
	@TableField("target_year")
	private String targetYear;
	
	
	@ApiModelProperty(value = "目标时间", name = "targetTime")
	@TableField("target_time")
	private String targetTime;

	@ApiModelProperty(value = "目标值", name = "targetValue")
	@TableField("target_value")
	private Integer targetValue;

	@ApiModelProperty(value = "create_time", name = "createTime")
	private Date createTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty("create_user")
	private Long createUser;

	@ApiModelProperty(value = "update_time", name = "updateTime")
	private Date updateTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "update_user", name = "updateUser")
	private Long updateUser;

}
