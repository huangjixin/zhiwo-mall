package com.fulan.api.elearning.common.domain;

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
 * 证书表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Certificate", description = "证书表")
@TableName("el_certificate")

public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "系统编号",name="id")
	private Long id;

@ApiModelProperty(value = "证书名称",name="name")
	private String name;

@ApiModelProperty(value = "证书说明",name="description")
	private String description;

@ApiModelProperty(value = "证书路径",name="path")
	private String path;

@ApiModelProperty(value = "是否删除1删除0未删除",name="isDelete")
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
