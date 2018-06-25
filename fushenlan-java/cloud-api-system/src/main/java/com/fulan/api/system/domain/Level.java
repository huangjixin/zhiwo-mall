package com.fulan.api.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 职业等级表
 * </p>
 *
 * @author Hedge
 * @since 2018-03-06
 */
@Data
@Api(tags = "Level", description = "职业等级表")
public class Level implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "主键ID",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "代理人级别",name="level_name")
	private String levelName;

	@ApiModelProperty(value = "代理人级别简称",name="level_code")
	private String levelCode;

	@ApiModelProperty(value = "路径",name="level_type")
	private Integer levelType;

	@ApiModelProperty(value = "代理人层级",name="level_hierarchy")
	private String levelHierarchy;

	@ApiModelProperty(value = "排序",name="seq")
	private String seq;

	@ApiModelProperty(value = "上级id", name = "parent_id")
	private String parentId;

}
