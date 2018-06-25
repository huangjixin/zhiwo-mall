package com.fulan.api.plan.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 班级计划
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "ClassPlan", description = "班级计划")
@TableName("el_class_plan")

public class ClassPlan implements Serializable {

    private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "班级计划编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "班级计划名称",name="name")
	private String name;

	@ApiModelProperty(value = "班级计划代码",name="code")
	private String code;

	@ApiModelProperty(value = "班级计划说明",name="description")
	private String description;

	@ApiModelProperty(value = "一级分类",name="groupId")
	@TableField("group_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;

	@ApiModelProperty(value = "二级分类",name="tagId")
	@TableField("tag_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;

	@ApiModelProperty(value = "是否培训新人",name="isTrainingNew")
	@TableField("is_training_new")
	private Integer isTrainingNew;

	@ApiModelProperty(value = "是否培训外勤",name="isTrainingAgent")
	@TableField("is_training_agent")
	private Integer isTrainingAgent;

	@ApiModelProperty(value = "是否培训内勤",name="isTrainingStaff")
	@TableField("is_training_staff")
	private Integer isTrainingStaff;
	
	@ApiModelProperty(value = "新人学习完成后是否签约",name="isSign")
	@TableField("is_sign")
	private Integer isSign;

	@ApiModelProperty(value = "人数限制",name="userLimit")
	@TableField("user_limit")
	private Integer userLimit;
	
	@ApiModelProperty(value = "开始日期",name="startDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@TableField("start_date")
	private Date startDate;

	@ApiModelProperty(value = "结束日期",name="endDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@TableField("end_date")
	private Date endDate;

	@ApiModelProperty(value = "否需积分兑换0/1（1:免费）",name="isFree")
	@TableField("is_free")
	private Integer isFree;

	@ApiModelProperty(value = "兑换所需积分",name="exchangePoint")
	@TableField("exchange_point")
	private Integer exchangePoint;

	@ApiModelProperty(value = "是否奖励积分 0/1（1表示奖励）",name="isRewardPoint")
	@TableField("is_reward_point")
	private Integer isRewardPoint;

	@ApiModelProperty(value = "是否奖励证书 0/1（1表示奖励）",name="isRewardCertification")
	@TableField("is_reward_certification")
	private Integer isRewardCertification;

	@ApiModelProperty(value = "是否奖励资格 0/1（1表示奖励）",name="isRewardQualification")
	@TableField("is_reward_qualification")
	private Integer isRewardQualification;

	@ApiModelProperty(value = "可获积分",name="rewardPoint")
	@TableField("reward_point")
	private Integer rewardPoint;

	/*@ApiModelProperty(value = "证书路径",name="certificationPath")
	@TableField("certification_path")
	private String certificationPath;*/

	/*@ApiModelProperty(value = "资格",name="qualification")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long qualification;*/
	
	@ApiModelProperty(value = "证书编号",name="isAllowExpired")
	@TableField("certification_id")
	private Long certificationId;
	
	@ApiModelProperty(value = "资格编号",name="isAllowExpired")
	@TableField("qualification_id")
	private Long qualificationId;

	@ApiModelProperty(value = "是否允许过期后继续 0/1（1:允许）",name="isAllowExpired")
	@TableField("is_allow_expired")
	private Integer isAllowExpired;

	@ApiModelProperty(value = "计划状态 启用/停用",name="state")
	private Integer state;

	@ApiModelProperty(value = "过期提醒 0/1（1:提醒）",name="isExpiredAlarm")
	@TableField("is_expired_alarm")
	private Integer isExpiredAlarm;

	@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

	@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;
	
	@ApiModelProperty(value = "计划类型 (0:普通型计划 1:阶段性计划)",name="planType")
    @TableField("plan_type")
    private Integer planType;

	@ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
	@TableField("is_seq")
	private Integer isSeq;


}
