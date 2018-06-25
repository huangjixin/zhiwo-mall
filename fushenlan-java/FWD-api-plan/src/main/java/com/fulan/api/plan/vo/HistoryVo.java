package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * yangzexu
 */
@Data
@Api(tags = "HistoryVo", description = "历史记录")
public class HistoryVo {

    @ApiModelProperty(value = "记录ID", name = "historyId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long historyId;//记录ID

    @ApiModelProperty(value = "计划ID", name = "planId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "计划类型", name = "planType")
    private Integer planType;

    @ApiModelProperty(value = "是否奖励积分", name = "isRewardPoint")
    private Long isRewardPoint;

    @ApiModelProperty(value = "是否奖励证书", name = "isRewardCertification")
    private Long isRewardCertification;

    @ApiModelProperty(value = "是否奖励资格", name = "isRewardQualification")
    private Long isRewardQualification;

    @ApiModelProperty(value = "计划名称", name = "name")
    private String name;

    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    @ApiModelProperty(value = "完成进度",name="learningProgress")
    private String learningProgress;

    @ApiModelProperty(value = "课程类型",name="courseVos")
    private List<CourseVo> courseVos;

    @ApiModelProperty(value = "图片路径",name="bannerPath")
    private String bannerPath;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date enterStartDate;

    private Long enterStartTime;

    @ApiModelProperty(value = "开始时间 当open_class_type为1时有效", name = "startDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startDate;

    private Long startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endDate;

    private Long endTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date enterEndDate;

    private Long enterEndTime;

    private Integer isNeedSign;

    private Integer isNeedEnter;

    private Integer isNoneedEnter;

    private Integer signTimeType;

    private Integer beforeBegin;

    private Integer afterBegin;

    private String address;

    private Integer rewardPoint;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long certificationId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long qualificationId;

    private Integer limitEnter;

    private String certificationName;

    private String qualificationName;

    private Integer signUpNumber;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseCollectId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long enterId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long signId;

    private Integer hasAuthority;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long commentId;




}
