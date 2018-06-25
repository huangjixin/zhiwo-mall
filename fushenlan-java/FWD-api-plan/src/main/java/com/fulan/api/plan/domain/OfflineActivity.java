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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 线下活动
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "OfflineActivity", description = "线下活动")
@TableName("el_offline_activity")

public class OfflineActivity implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "活动编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "基础课程编号-基础课程中的线下课程", name = "courseId")
    @TableField("course_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;

    @ApiModelProperty(value = "活动名称", name = "name")
    private String name;

    @ApiModelProperty(value = "活动说明", name = "description")
    private String description;

    @ApiModelProperty(value = "活动缩略图", name = "bannerPath")
    @TableField("banner_path")
    private String bannerPath;

    @ApiModelProperty(value = "活动开放时间类型-0长期有效，1指定时间", name = "openClassType")
    @TableField("open_class_type")
    private Integer openClassType;

    @ApiModelProperty(value = "开始时间 当open_class_type为1时有效", name = "startDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("start_date")
    private Date startDate;


    @ApiModelProperty(value = "结束日期 当open_class_type为1时有效", name = "endDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("end_date")
    private Date endDate;
    
    @ApiModelProperty(value = "报名开始时间", name = "enterStartDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("enter_start_date")
    private Date enterStartDate;
    
    @ApiModelProperty(value = "报名结束时间", name = "enterEndDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("enter_end_date")
    private Date enterEndDate;
    
    @ApiModelProperty(value = "报名类型- 是否需要报名，1需要，0不需要", name = "isNeedEnter")
    @TableField("is_need_enter")
    private Integer isNeedEnter;

    @ApiModelProperty(value = "签到类型- 是否需要签到，1需要，0不需要", name = "isNeedSign")
    @TableField("is_need_sign")
    private Integer isNeedSign;

    @ApiModelProperty(value = "签到类型-不需要报名也能签到，1能，0不能", name = "isNoneedEnter")
    @TableField("is_noneed_enter")
    private Integer isNoneedEnter;

    @ApiModelProperty(value = "签到时间类型-1课程开始前，2课程开始前到课程开始后，3课程开始后", name = "signTimeType")
    @TableField("sign_time_type")
    private Integer signTimeType;

    @ApiModelProperty(value = "课程开始前", name = "beforeBegin")
    @TableField("before_begin")
    private Integer beforeBegin;

    @ApiModelProperty(value = "课程开始后", name = "afterBegin")
    @TableField("after_begin")
    private Integer afterBegin;

    @ApiModelProperty(value = "上课地点", name = "address")
    private String address;

    @ApiModelProperty(value = "是否奖励积分-0/1（1表示奖励）", name = "isRewardPoint")
    @TableField("is_reward_point")
    private Integer isRewardPoint;

    @ApiModelProperty(value = "是否奖励证书-0/1（1表示奖励）", name = "isRewardCertification")
    @TableField("is_reward_certification")
    private Integer isRewardCertification;

    @ApiModelProperty(value = "是否奖励资格-0/1（1表示奖励）", name = "isRewardQualification")
    @TableField("is_reward_qualification")
    private Integer isRewardQualification;

    @ApiModelProperty(value = "可获积分", name = "rewardPoint")
    @TableField("reward_point")
    private Integer rewardPoint;

    @ApiModelProperty(value = "证书编号", name = "certificationId")
    @TableField("certification_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long certificationId;

    @ApiModelProperty(value = "资格编号", name = "qualificationId")
    @TableField("qualification_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long qualificationId;

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
    
    @ApiModelProperty(value = "线下活动状态", name = "state")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "报名人数限制",name="limitEnter")
    @TableField("limit_enter")
    private Integer limitEnter;

    @ApiModelProperty(value = "公开范围（0:所有人   1:特定对象。2:游客及登录用户）",name="openRange")
    @TableField("open_range")
    private Integer openRange;


}
