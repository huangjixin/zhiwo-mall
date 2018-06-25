package com.fulan.api.erecruitment.common.domain;

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
 * 每道题的流水得分情况
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PersonnelPaperInfo", description = "每道题的流水得分情况")
@TableName("er_personnel_paper_info")

public class PersonnelPaperInfo implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "人才id",name="personnelId")
	@TableField("personnel_id")
	private Long personnelId;

@ApiModelProperty(value = "考卷id",name="paperId")
	@TableField("paper_id")
	private Long paperId;

@ApiModelProperty(value = "考卷题目id",name="paperItemId")
	@TableField("paper_item_id")
	private Long paperItemId;

@ApiModelProperty(value = "得分",name="score")
	private Integer score;

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
