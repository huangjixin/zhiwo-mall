package com.fulan.api.information.domain;

import java.io.Serializable;

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
 * 资讯指定部门映射表
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "MessageDeptMapping", description = "资讯指定部门映射表")
@TableName("iris_message_dept_mapping")

public class MessageDeptMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	
	@ApiModelProperty(value = "资讯编号", name = "newsId")
	@TableField("news_id")
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	private Long newsId;

	@ApiModelProperty(value = "部门编号", name = "deptId")
	@TableField("dept_id")
	private String deptId;
	
	
	@ApiModelProperty(value = "公司编号", name = "deptId")
	@TableField("company_id")
	private String companyId;
	
	

	@ApiModelProperty(value = "代理人编号/工号", name = "agentCode")
	@TableField("agent_code")
	private String agentCode;

	@ApiModelProperty(value = "1_指定人员  2_指定部门  ", name = "targetType")
	@TableField("target_type")
	private Integer targetType;

}
