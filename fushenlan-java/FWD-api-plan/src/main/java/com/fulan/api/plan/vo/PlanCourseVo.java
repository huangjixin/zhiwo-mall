package com.fulan.api.plan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class PlanCourseVo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "计划课程关联id", name = "id")
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "计划id", name = "planId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "计划类型-公开课/学习计划/班级计划/岗位发展/必修任务", name = "courseType")
    private Integer courseType;

    @ApiModelProperty(value = "基础课程编号", name = "associateId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long associateId;

    @ApiModelProperty(value = "关联类别-试卷/线上课程/线下课程", name = "associateType")
    private Integer associateType;

    @ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
    private Integer isCompulsory;

    @ApiModelProperty(value = "所属阶段", name = "stage")
    private Integer stage;

    @ApiModelProperty(value = "排序", name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "开始时间", name = "startTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "截止时间", name = "endTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "签到地点", name = "signAddress")
    private String signAddress;

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

    private Integer paperStatus;//考试状态

    private Integer courseStatus;//课程状态

    private Long currentTime = new Date().getTime();//当前系统时间

    private Long sTime;

    private Long eTime;

    private List<CourseVo> courses;

    private List<PaperVo> papers;
    
    private List<PostVO> postVO;

}
