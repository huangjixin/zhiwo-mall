package com.fulan.api.elearning.material.domain;

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
 * 讲师资料分享，分享对象为用户组/角色
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "MaterialShare", description = "讲师资料分享，分享对象为用户组/角色")
@TableName("el_material_share")

public class MaterialShare implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "系统编号",name="id")
	private Long id;

@ApiModelProperty(value = "用户组编号",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "资料编号",name="materialId")
	@TableField("material_id")
	private Long materialId;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
