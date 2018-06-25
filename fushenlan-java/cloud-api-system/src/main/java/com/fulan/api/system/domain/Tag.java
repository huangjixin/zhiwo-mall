package com.fulan.api.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@ApiModel(value="标签表",description="标签表")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(notes = "编号")
@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

@ApiModelProperty(notes = "表签名")
	private String tagName;

@ApiModelProperty(notes = "上级编号")
@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long parentId;

@ApiModelProperty(notes = "分类")
	private Integer catagory;

@ApiModelProperty(notes = "所属系统")
	private Integer moudle;

@ApiModelProperty(notes = "创建人")
@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

@ApiModelProperty(notes = "创建时间")
	private Date createTime;

@ApiModelProperty(notes = "更新人")
@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

@ApiModelProperty(notes = "更新时间")
	private Date modifyTime;


}
