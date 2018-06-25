package com.fulan.api.plan.vo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 基础课程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "HotPublicClassVo", description = "热度最高的公开课程")
public class HotPublicClassVo implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID",name="id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    @ApiModelProperty(value = "计划ID",name="planId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;
    @ApiModelProperty(value = "观看人数",name="num")
    private Integer num;
    @ApiModelProperty(value = "课程名称",name="name")
    private String name;
    @ApiModelProperty(value = "课程类型",name="type")
    private Integer type;
    @ApiModelProperty(value = "图片路径",name="bannerPath")
    private String bannerPath;
    @ApiModelProperty(value = "讲师Id",name="lecturerId")
    private Integer lecturerId;
    @ApiModelProperty(value = "讲师名称",name="lecturerName")
    private String lecturerName;
    @ApiModelProperty(value = "讲师职位",name="position")
    private String position;
    @ApiModelProperty(value = "是否奖励积分",name="isRewardPoint")
    private Integer isRewardPoint;
    @ApiModelProperty(value = "是否免费",name="isFree")
    private Integer isFree;
    @ApiModelProperty(value = "奖励积分",name="rewardPoint")
    private Integer rewardPoint;
    @ApiModelProperty(value = "兑换积分",name="exchangePoint")
    private Integer exchangePoint;
    @ApiModelProperty(value = "是否奖励资格",name="isRewardCertification")
    private Integer isRewardCertification;
    @ApiModelProperty(value = "是否奖励证书",name="isRewardQualification")
    private Integer isRewardQualification;
    private String courseType;

}
