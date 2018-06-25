package com.fulan.api.information.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
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
 * 活动
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "Activity", description = "活动")
@TableName("iris_activity")

public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "主键/编号",name="id")
	@TableId(value="id", type= IdType.AUTO)
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String code;
	
	private String type;

@ApiModelProperty(value = "活动名称",name="title")
	private String title;

@ApiModelProperty(value = "活动缩率图(路径)",name="path")
	private String path;

@ApiModelProperty(value = "附件(路径)",name="attachment_path")
private String attachmentPath;

@ApiModelProperty(value = "活动内容",name="content")
	private String content;

@ApiModelProperty(value = "格式",name="contentType")
	@TableField("content_type")
	private Integer contentType;


	@ApiModelProperty(value = "外部链接url", name = "externalLinkUrl")
	@TableField("external_link_url")
	private String externalLinkUrl;

	@TableField("is_secret")
	private Integer isSecret;

	@ApiModelProperty(value = "0_关闭,1_开启", name = "openShare")
	@TableField("open_share")
	private Integer openShare;

@ApiModelProperty(value = "活动地址",name="activityAddress")
	@TableField("activity_address")
	private String activityAddress;

@ApiModelProperty(value = "活动开始时间",name="actStartDate")
	@TableField("act_start_date")
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date actStartDate;

@ApiModelProperty(value = "活动结束时间",name="actEndDate")
	@TableField("act_end_date")
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date actEndDate;

@ApiModelProperty(value = "报名开始时间",name="signUpStartDate")
	@TableField("sign_up_start_date")
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date signUpStartDate;

@ApiModelProperty(value = "报名结束时间",name="signUpEndDate")
	@TableField("sign_up_end_date")
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date signUpEndDate;

@ApiModelProperty(value = "1_不限定人数,2_限定数量,3_指定人员.",name="signUpSet")
	@TableField("sign_up_set")
	private Integer signUpSet;

@ApiModelProperty(value = "活动限定数量",name="limitNumber")
	@TableField("limit_number")
	private Integer limitNumber;



@ApiModelProperty(value = "1_所有人 2_指定部门 3_指定人员  ",name="specifyType")
	@TableField("specify_type")
	private Integer specifyType;

@ApiModelProperty(value = "1_即使发送  2_定时发送",name="sendType")
	@TableField("send_type")
	private Integer sendType;

@ApiModelProperty(value = "定时发送时间",name="timing")
   @TableField("timing")
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date timing;

@ApiModelProperty(value = "是否有效",name="isActive")
	@TableField("is_active")
	private Integer isActive;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date gmtModified;

@ApiModelProperty(value = "是否删除,0_否,1_是",name="deleted")
	@TableField("deleted")
	private Integer deleted;


	@ApiModelProperty(value = "信息状态：0_未发布,1_己撤回,2_已发布",name="status")
	@TableField("status")
	private Integer status ;


@ApiModelProperty(value = "备注",name="remark")
	@TableField("remark")
	private String remark;
	

@ApiModelProperty(value = "备注字段",name="bak1")
	@TableField("bak1")
	private String bak1;

@ApiModelProperty(value = "备注字段",name="bak2")
	@TableField("bak2")
	private String bak2;

@ApiModelProperty(value = "备注字段",name="bak3")
	@TableField("bak3")
	private String bak3;

@ApiModelProperty(value = "备用字段",name="bak4")
	@TableField("bak4")
	private String bak4;

@ApiModelProperty(value = "备注字段",name="bak5")
	@TableField("bak5")
	private String bak5;



}
