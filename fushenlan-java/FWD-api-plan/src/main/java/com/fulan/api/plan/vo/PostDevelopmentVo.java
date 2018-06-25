package com.fulan.api.plan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PostDevelopmentVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "岗位发展编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "岗位名称", name = "name")
    private String name;

    @ApiModelProperty(value = "岗位说明", name = "description")
    private String description;

    @ApiModelProperty(value = "是否允许过期后继续", name = "isAllowExpired")
    private Integer isAllowExpired;

    @ApiModelProperty(value = "计划限制（岗位）", name = "limitTypes")
    private Integer limitTypes;

    @ApiModelProperty(value = "升级后天数（开始）", name = "startDays")
    private Integer startDays;

    @ApiModelProperty(value = "升级后天数（结束）", name = "endDays")
    private Integer endDays;

    @ApiModelProperty(value = "岗位等级", name = "postLevel")
    private String postLevel;

    @ApiModelProperty(value = "计划状态", name = "state")
    private Integer state;

    @ApiModelProperty(value = "过期提醒", name = "isExpiredAlarm")
    private Integer isExpiredAlarm;

    @ApiModelProperty(value = "创建人", name = "createUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "公开范围（0:所有人   1:特定对象。2:游客及登录用户）",name="openRange")
    private Integer openRange;


    @ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
    private Integer isSeq;


    private String learningProgress;//学习进度

    private Integer isFinished;

    private Integer planType;

    private List<PlanCourseVo> planCourses;

    private List<CourseVo> courses;

}
