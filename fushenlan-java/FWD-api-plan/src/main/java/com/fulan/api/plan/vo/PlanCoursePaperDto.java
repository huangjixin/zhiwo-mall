package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学习大纲数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
@Data
public class PlanCoursePaperDto implements Serializable{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "计划课程关联id", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "计划id", name = "planId")
    @TableField("plan_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "计划类型-公开课/学习计划/班级计划/岗位发展/必修任务", name = "courseType")
    @TableField("course_type")
    private Integer courseType;

    @ApiModelProperty(value = "基础课程编号", name = "associateId")
    @TableField("associate_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long associateId;

    @ApiModelProperty(value = "关联类别-试卷/线上课程/线下课程", name = "associateType")
    @TableField("associate_type")
    private Integer associateType;

    @ApiModelProperty(value = "所属阶段", name = "stage")
    private Integer stage;

    @ApiModelProperty(value = "名称",name="name")
    private String name;

    @ApiModelProperty(value = "说明",name="discription")
    private String discription;

    @ApiModelProperty(value = "答题时间/至少学习时间",name="learningTime")
    private Integer learningTime;

    @ApiModelProperty(value = "类型",name="type")
    private Integer type;

    @ApiModelProperty(value = "是否通过考试/是否已完成考试",name="finishFlag")
    private Integer finishFlag;

    @ApiModelProperty(value = "是否可再次考试",name="canExam")
    private Integer canExam;

    @ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
    @TableField("is_compulsory")
    private Integer isCompulsory;
    
    @ApiModelProperty(value = "线下课程开始时间",name="startTime")
    private Date startTime;
    
    @ApiModelProperty(value = "线下课程结束时间",name="endTime")
    private Date endTime;
    
    @ApiModelProperty(value = "线下课程签到地点",name="signAddress")
    private String signAddress;

}
