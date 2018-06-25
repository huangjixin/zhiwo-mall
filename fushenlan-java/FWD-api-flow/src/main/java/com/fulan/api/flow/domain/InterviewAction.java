package com.fulan.api.flow.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 面试执行情况
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Data
@Api(tags = "InterviewAction", description = "面试执行情况")
@TableName("er_interview_action")

public class InterviewAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "主键", name = "id")
	private Long id;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "人才id", name = "personnelId")
	private Long personnelId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "流程模块id", name = "flowActionId")
    private Long flowActionId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "需要处理的模块", name = "flowItemId")
	private Long flowItemId;

	@ApiModelProperty(value = "开始时间", name = "startTime")
	@TableField("start_time")
	private Date startTime;

	@ApiModelProperty(value = "结束时间", name = "endTime")
	private Date endTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "面试官", name = "processingPerson")
    private Long processingPerson;
	
	@ApiModelProperty(value = "面试官名字", name = "processingName")
	@TableField("processing_name")
    private String processingName;  

	@ApiModelProperty(value = "面试结果(1,通过，2不通过，3，更改计划)", name = "processingStatus")
	private String processingStatus;

	@ApiModelProperty(value = "面试描述", name = "processingDesc")
	private String processingDesc;

	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "创建人", name = "createUser")
	private Long createUser;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "跟新人", name = "updateUser")
    private Long updateUser;

	@ApiModelProperty(value = "跟新时间", name = "updateTime")
	private Date updateTime;

}
