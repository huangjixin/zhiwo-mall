package com.fulan.api.security.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
@Data
@Api(tags = "UserGroup", description = "用户组")
@TableName("user_group")

public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "系统编号",name="id")
    @IdAnnon
	private Long id;

    @ApiModelProperty(value = "用户组名称",name="groupName")
	@TableField("group_name")
	private String groupName;

    @ApiModelProperty(value = "用户组说明",name="groupDesc")
	@TableField("group_desc")
	private String groupDesc;

    @ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

    @ApiModelProperty(value = "创建时间",name="createTime")
    @TableField("create_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;

    @ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

    @ApiModelProperty(value = "修改时间",name="modifyTime")
    @TableField("modify_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date modifyTime;
    
}
