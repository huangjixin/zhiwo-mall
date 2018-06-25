package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;

import java.math.BigDecimal;
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
 * 公开课表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PublicClass", description = "公开课表")
@TableName("el_public_class")

public class PublicClass implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "公开课编号",name="id")
	private Long id;

@ApiModelProperty(value = "计划编号",name="planId")
	@TableField("plan_id")
	private Long planId;

@ApiModelProperty(value = "一级分类",name="tagId")
	@TableField("tag_id")
	private Long tagId;

@ApiModelProperty(value = "二级分类",name="childTagId")
	@TableField("child_tag_id")
	private Long childTagId;

@ApiModelProperty(value = "0/1（1表示免费）",name="isFree")
	@TableField("is_free")
	private Integer isFree;

@ApiModelProperty(value = "兑换积分",name="exchangePoint")
	@TableField("exchange_point")
	private Integer exchangePoint;

@ApiModelProperty(value = "0/1（1表示是）",name="isRewardPoint")
	@TableField("is_reward_point")
	private Integer isRewardPoint;

@ApiModelProperty(value = "0/1（1表示是）",name="isRewardCertification")
	@TableField("is_reward_certification")
	private Integer isRewardCertification;

@ApiModelProperty(value = "0/1（1表示是）",name="isRewardQualification")
	@TableField("is_reward_qualification")
	private Integer isRewardQualification;

@ApiModelProperty(value = "可获积分",name="rewardPoint")
	@TableField("reward_point")
	private Integer rewardPoint;

@ApiModelProperty(value = "证书路径",name="certificationPath")
	@TableField("certification_path")
	private String certificationPath;

@ApiModelProperty(value = "资格",name="qualification")
	private Long qualification;

@ApiModelProperty(value = "开课时间类型-1长期有效/2指定日期",name="isLongTerm")
	@TableField("is_long_term")
	private Integer isLongTerm;

@ApiModelProperty(value = "开始日期",name="startDate")
	@TableField("start_date")
	private Date startDate;

@ApiModelProperty(value = "结束日期",name="endDate")
	@TableField("end_date")
	private Date endDate;

@ApiModelProperty(value = "上架时间",name="onlineDate")
	@TableField("online_date")
	private Date onlineDate;

@ApiModelProperty(value = "下架时间",name="offlineDate")
	@TableField("offline_date")
	private Date offlineDate;

@ApiModelProperty(value = "公开课状态",name="state")
	private Integer state;

@ApiModelProperty(value = "是否开放问答",name="isQuestion")
	@TableField("is_question")
	private Integer isQuestion;

@ApiModelProperty(value = "是否置顶课程",name="isSticky")
	@TableField("is_sticky")
	private Integer isSticky;

@ApiModelProperty(value = "置顶排序",name="stickySeq")
	@TableField("sticky_seq")
	private Integer stickySeq;

@ApiModelProperty(value = "星级评分",name="score")
	private BigDecimal score;

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
