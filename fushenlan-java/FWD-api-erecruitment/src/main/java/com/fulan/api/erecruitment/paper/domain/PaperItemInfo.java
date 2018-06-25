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
 * 题目详细内容
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PaperItemInfo", description = "题目详细内容")
@TableName("er_paper_item_info")

public class PaperItemInfo implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "考卷id",name="paperItemId")
	@TableField("paper_item_id")
	private Long paperItemId;

@ApiModelProperty(value = "考题内容",name="paperContent")
	@TableField("paper_content")
	private String paperContent;

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
