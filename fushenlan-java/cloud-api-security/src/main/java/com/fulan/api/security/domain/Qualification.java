package com.fulan.api.security.domain;

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
 * 资格表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Qualification", description = "资格表")
@TableName("el_qualification")

public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "资格名称",name="name")
	private String name;

@ApiModelProperty(value = "资格说明",name="description")
	private String description;

@ApiModelProperty(value = "是否删除",name="isDelete")
	@TableField("is_delete")
	private Integer isDelete;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
