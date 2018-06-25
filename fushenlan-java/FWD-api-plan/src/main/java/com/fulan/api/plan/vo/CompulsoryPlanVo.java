package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;


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
@Api(tags = "CompulsoryPlanVo", description = "必修任务表")
public class CompulsoryPlanVo implements Serializable,Comparable<CompulsoryPlanVo> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "任务编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "任务名称", name = "name")
    private String name;

    @ApiModelProperty(value = "任务代码", name = "code")
    private String code;

    @ApiModelProperty(value = "任务说明", name = "description")
    private String description;

    @ApiModelProperty(value = "一级分类", name = "groupId")
    @TableField("group_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long groupId;

    @ApiModelProperty(value = "二级分类", name = "tagId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(value = "开始日期", name = "startTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束日期", name = "endTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "是否奖励积分", name = "isRewardPoint")
    private Integer isRewardPoint;

    @ApiModelProperty(value = "是否奖励证书", name = "isRewardCertification")
    private Integer isRewardCertification;

    @ApiModelProperty(value = "是否奖励资格", name = "isRewardQualification")
    private Integer isRewardQualification;

    @ApiModelProperty(value = "可获积分", name = "rewardPoint")
    private Integer rewardPoint;

    @ApiModelProperty(value = "证书路径", name = "certificationId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long certificationId;

    @ApiModelProperty(value = "资格", name = "qualificationId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long qualificationId;

    @ApiModelProperty(value = "是否允许过期后继续", name = "isAllowExpired")
    private Integer isAllowExpired;

    @ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)",name="associateId")
    private Long associateId;

    @ApiModelProperty(value = "计划状态", name = "state")
    private Integer state;

    @ApiModelProperty(value = "过期提醒", name = "isExpiredAlarm")
    private Integer isExpiredAlarm;

    @ApiModelProperty(value = "创建人", name = "createUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    private Date gmtModified;

    @ApiModelProperty(value = "学习进度", name = "learningProgress")
    private String learningProgress;//

    @ApiModelProperty(value = "计划完成标识", name = "isFinished")
    private Integer isFinished;

    @ApiModelProperty(value = "计划类型", name = "planType")
    private Integer planType;

    private List<PlanCourseVo> planCourses;


    @ApiModelProperty(value = "开始日期时间戳", name = "startTimeStamp")
    private Long startTimeStamp;

    @ApiModelProperty(value = "结束日期时间戳", name = "endTimeStamp")
    private Long endTimeStamp;

    @ApiModelProperty(value = "公开范围（0:所有人   1:特定对象。2:游客及登录用户）",name="openRange")
    private Integer openRange;


    @ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
    private Integer isSeq;

    @Override
    public int compareTo(CompulsoryPlanVo o) {
        return compare(this.isFinished,o.isFinished);
    }

    /**
     * @param param1
     * @param param2
     * @return
     */
    public static int compare(Integer param1, Integer param2) {
        return (param1 > param2 ? 1 :
                (param1 == param2 ? 0 : -1));
    }

    public Long getStartTimeStamp() {
        return this.getStartTime()==null?null:this.getStartTime().getTime();
    }

    public Long getEndTimeStamp() {
        return this.getEndTime()==null?null:this.getEndTime().getTime();
    }
}
