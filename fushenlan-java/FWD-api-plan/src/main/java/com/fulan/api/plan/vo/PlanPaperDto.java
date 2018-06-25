package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 课程大纲试卷数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Data
public class PlanPaperDto implements Serializable {
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

    @ApiModelProperty(value = "试卷说明",name="discription")
    private String discription;

    @ApiModelProperty(value = "职业规范/思维方法/演讲口才/销售技巧/保险产品",name="type")
    private Integer type;

    @ApiModelProperty(value = "答题时间",name="examTime")
    private Integer examTime;

    @ApiModelProperty(value = "试卷满分",name="fullMark")
    private Integer fullMark;

    @ApiModelProperty(value = "通过分数",name="passMark")
    private Integer passMark;

    @ApiModelProperty(value = "可测试次数",name="testNum")
    private Integer testNum;

    @ApiModelProperty(value = "是否人工阅卷,1是，0否",name="isArtificialMark")
    private Integer isArtificialMark;

    @ApiModelProperty(value = "第几次考试",name="examNum")
    private Integer examNum;

    @ApiModelProperty(value = "是否通过考试",name="passFlag")
    private Integer passFlag;

}
