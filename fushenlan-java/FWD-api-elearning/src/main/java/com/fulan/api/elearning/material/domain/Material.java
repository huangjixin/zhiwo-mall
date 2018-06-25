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
 * 资料表，讲师，管理员共用
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Material", description = "资料表，讲师，管理员共用")
@TableName("el_material")

public class Material implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "资料编号",name="id")
	private Long id;

@ApiModelProperty(value = "资料名称",name="name")
	private String name;

@ApiModelProperty(value = "资料类型(视频/SCORM/PPT/WORD/EXCEL)",name="type")
	private Integer type;

@ApiModelProperty(value = "资料说明",name="description")
	private String description;

@ApiModelProperty(value = "一级分类(用户组)",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "二级分类(用户组下面的标签)",name="tagId")
	@TableField("tag_id")
	private Long tagId;

@ApiModelProperty(value = "是否分享(1 是， 0 否)",name="isShare")
	@TableField("is_share")
	private Integer isShare;

@ApiModelProperty(value = "是否关联计划(1 是， 0 否)",name="isRelatePlan")
	@TableField("is_relate_plan")
	private Integer isRelatePlan;

@ApiModelProperty(value = "提交者",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "上传时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
