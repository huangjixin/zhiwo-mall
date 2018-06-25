package com.fulan.api.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Data
@Api(tags = "Version", description = "")

public class Version implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "版本号",name="versionCode")
	private String versionCode;

	@ApiModelProperty(value = "版本类型",name="type")
	private Integer type;
	
	@ApiModelProperty(value = "创建人",name="createUser")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@ApiModelProperty(value = "创建时间",name="createTime")
	private Date createTime;

	@ApiModelProperty(value = "更新人",name="modifyUser")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	@ApiModelProperty(value = "更新时间",name="modifyTime")
	private Date modifyTime;



}
