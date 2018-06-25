package com.fulan.api.personnel.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
@ApiModelProperty(value = "",name="id")
	private Long id;
@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
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

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "推荐人开始日期",name="refereeStartTime")
	@TableField("referee_start_time")
	private String refereeStartTime;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "创建时间",name="createTime")
	@TableField("create_time")
	private Date createTime;
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "更新时间",name="updateTime")
	@TableField("update_time")
	private Date updateTime;
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "更新人",name="updateUser")
	@TableField("update_user")
	private Long updateUser;
	
	@ApiModelProperty(value = "初审是否通过(0通过,1不通过)",name="trialResult")
	@TableField("trial_result")
private String trialResult;


}
