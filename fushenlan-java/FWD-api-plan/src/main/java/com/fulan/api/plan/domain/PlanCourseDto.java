package com.fulan.api.plan.domain;

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
 * 计划与课程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanCourseDto", description = "计划与课程数据对象")
public class PlanCourseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4813829200646079928L;

    @ApiModelProperty(value = "基础课程编号", name = "associateId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long associateId;

    @ApiModelProperty(value = "关联类别-试卷/线上课程/线下课程", name = "associateType")
    private Integer associateType;

    @ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
    private Integer isCompulsory;

    @ApiModelProperty(value = "顺序", name = "seq")
    private Integer seq;
    
    @ApiModelProperty(value = "所属阶段", name = "stage")
    private Integer stage;

    @ApiModelProperty(value = "开始时间", name = "startTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "截止时间", name = "endTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "签到地点", name = "signAddress")
    private String signAddress;

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public Integer getAssociateType() {
        return associateType;
    }

    public void setAssociateType(Integer associateType) {
        this.associateType = associateType;
    }

    public Integer getIsCompulsory() {
        return isCompulsory;
    }

    public void setIsCompulsory(Integer isCompulsory) {
        this.isCompulsory = isCompulsory;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }
    
}
