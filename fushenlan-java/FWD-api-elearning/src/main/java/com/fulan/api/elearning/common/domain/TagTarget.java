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
 * 标签关系表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "TagTarget", description = "标签关系表")
@TableName("el_tag_target")

public class TagTarget implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "类型  1:用户组2:公开课",name="type")
	private Integer type;

@ApiModelProperty(value = "关联编号",name="hostId")
	@TableField("host_id")
	private Long hostId;

@ApiModelProperty(value = "创建时间",name="createTime")
	@TableField(value = "create_time")
	private Date createTime;



}
