package com.fulan.api.erecruitment.personnel.domain;

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
 * 家庭成员
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "FamilyMember", description = "家庭成员")
@TableName("er_family_member")

public class FamilyMember implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "",name="id")
	private Long id;

@ApiModelProperty(value = "人才id",name="personnelId")
	@TableField("personnel_id")
	private Long personnelId;

@ApiModelProperty(value = "姓名",name="name")
	private String name;

@ApiModelProperty(value = "关系",name="relationship")
	private String relationship;

@ApiModelProperty(value = "代理人id",name="agentCode")
	@TableField("agent_code")
	private String agentCode;

@ApiModelProperty(value = "电话",name="telephone")
	private String telephone;

@ApiModelProperty(value = "所属分公司",name="company")
	private String company;

@ApiModelProperty(value = "渠道",name="channel")
	private String channel;

@ApiModelProperty(value = "职务",name="post")
	private String post;

@ApiModelProperty(value = "入职日期",name="entryDate")
	@TableField("entry_date")
	private String entryDate;

@ApiModelProperty(value = "顺序",name="seq")
	private Integer seq;

@ApiModelProperty(value = "创建时间",name="createTime")
	@TableField("create_time")
	private Date createTime;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "更新时间",name="updateTime")
	@TableField("update_time")
	private Date updateTime;

@ApiModelProperty(value = "更新人",name="updateUser")
	@TableField("update_user")
	private Long updateUser;



}
