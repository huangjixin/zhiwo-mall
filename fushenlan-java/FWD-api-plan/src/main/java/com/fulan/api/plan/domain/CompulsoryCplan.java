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
 * 必修任务表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "CompulsoryCplan", description = "必修任务表")
@TableName("el_compulsory_cplan")

public class CompulsoryCplan implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "任务编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    
    @ApiModelProperty(value = "必修任务代码", name = "code")
    private String code;

    @ApiModelProperty(value = "任务名称", name = "name")
    private String name;

    @ApiModelProperty(value = "任务说明", name = "description")
    private String description;

    @ApiModelProperty(value = "一级分类", name = "groupId")
    @TableField("group_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long groupId;

    @ApiModelProperty(value = "二级分类", name = "tagId")
    @TableField("tag_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(value = "开始日期", name = "startDate")
    @TableField("start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startDate;
     
    @ApiModelProperty(value = "结束日期", name = "endDate")
    @TableField("end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;

    @ApiModelProperty(value = "是否奖励积分", name = "isRewardPoint")
    @TableField("is_reward_point")
    private Integer isRewardPoint;

    @ApiModelProperty(value = "是否奖励证书", name = "isRewardCertification")
    @TableField("is_reward_certification")
    private Integer isRewardCertification;

    @ApiModelProperty(value = "是否奖励资格", name = "isRewardQualification")
    @TableField("is_reward_qualification")
    private Integer isRewardQualification;

    @ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)",name="associateId")
    private Long associateId;

    @ApiModelProperty(value = "可获积分", name = "rewardPoint")
    @TableField("reward_point")
    private Integer rewardPoint;

    @ApiModelProperty(value = "证书路径", name = "certificationId")
    @TableField("certification_id")
    private Long certificationId;

    @ApiModelProperty(value = "资格", name = "qualificationId")
    private Long qualificationId;

    @ApiModelProperty(value = "是否允许过期后继续", name = "isAllowExpired")
    @TableField("is_allow_expired")
    private Integer isAllowExpired;

    @ApiModelProperty(value = "计划状态", name = "state")
    private Integer state;

    @ApiModelProperty(value = "过期提醒", name = "isExpiredAlarm")
    @TableField("is_expired_alarm")
    private Integer isExpiredAlarm;

    @ApiModelProperty(value = "创建人", name = "createUser")
    @TableField("create_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @TableField("modify_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;
    
    @ApiModelProperty(value = "计划类型 (0:普通型计划 1:阶段性计划)", name = "planType")
    @TableField("plan_type")
    private Integer planType;

    @ApiModelProperty(value = "公开范围（0:所有人   1:特定对象。2:游客及登录用户）",name="openRange")
    @TableField("open_range")
    private Integer openRange;


    @ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
    @TableField("is_seq")
    private Integer isSeq;


    @ApiModelProperty(value="字典code",name="dictCode",example="abc")
    @TableField("dict_code")
    private String dictCode;

}
