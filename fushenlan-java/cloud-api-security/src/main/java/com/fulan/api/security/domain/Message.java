package com.fulan.api.security.domain;

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
 * 
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-23
 */
@Data
@ApiModel(value = "Message", description = "消息实体类")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "主键", name = "id")
	private Long id;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "代理人id", name = "accountId")
	private Long accountId;

	@ApiModelProperty(value = "内容", name = "content")
	private String content;

	@ApiModelProperty(value = "消息类型", name = "type")
	private String type;

	@ApiModelProperty(value = "创建时间", name = "createTime")

	private Date createTime;

	@ApiModelProperty(value = "创建人", name = "createUser")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@ApiModelProperty(value = "跟新时间", name = "updateTime")
	private Date updateTime;
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "跟新人", name = "updateUser")
	private Long updateUser;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "代理人id", name = "personnelId")
	private Long personnelId;
	
	@ApiModelProperty(value = "消息状态", name = "status")
	private String status;					//消息状态 0：未读 1：已读

}
