package com.fulan.api.elearning.question.domain;

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
 * 试题管理，讲师，管理员共用
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Question", description = "试题管理，讲师，管理员共用")
@TableName("el_question")

public class Question implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "试题编号",name="id")
	private Long id;

@ApiModelProperty(value = "试题内容",name="content")
	private String content;

@ApiModelProperty(value = "单选/多选/判断/问答",name="type")
	private Integer type;

@ApiModelProperty(value = "试题解析",name="analysis")
	private String analysis;

@ApiModelProperty(value = "用户组/角色编号",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "用户组/角色下的标签编号",name="tagId")
	@TableField("tag_id")
	private Long tagId;

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
