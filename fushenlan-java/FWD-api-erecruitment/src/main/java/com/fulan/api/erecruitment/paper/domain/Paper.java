package com.fulan.api.erecruitment.paper.domain;

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
 * 试卷
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Paper", description = "试卷")
@TableName("er_paper")

public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "试卷名称",name="paperName")
	@TableField("paper_name")
	private String paperName;

@ApiModelProperty(value = "试卷类型(1.初审，2:甄选，3：决定)",name="paperType")
	@TableField("paper_type")
	private Integer paperType;

@ApiModelProperty(value = "试卷说明",name="paperDesc")
	@TableField("paper_desc")
	private String paperDesc;

@ApiModelProperty(value = "所属分公司",name="orgId")
	@TableField("org_id")
	private Long orgId;

@ApiModelProperty(value = "排序",name="seq")
	private Integer seq;

@ApiModelProperty(value = "",name="createTime")
	@TableField("create_time")
	private Date createTime;

@ApiModelProperty(value = "",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "",name="updateTime")
	@TableField("update_time")
	private Date updateTime;

@ApiModelProperty(value = "",name="updateUser")
	@TableField("update_user")
	private Long updateUser;



}
