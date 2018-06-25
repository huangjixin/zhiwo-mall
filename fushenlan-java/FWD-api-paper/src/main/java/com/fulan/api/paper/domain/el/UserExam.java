package com.fulan.api.paper.domain.el;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学员考试结果表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "UserExam", description = "学员考试结果表")
@TableName("el_user_exam")

public class UserExam implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号", name = "id")
    @IdAnnon
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "试卷编号", name = "paperId")
    @TableField("paper_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long paperId;

    @ApiModelProperty(value = "试题编号", name = "questionId")
    @TableField("question_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long questionId;

    @ApiModelProperty(value = "试题类型(单选/多选)", name = "questionType")
    @TableField("question_type")
    private Integer questionType;

    @ApiModelProperty(value = "试题状态(1已阅卷/2待阅卷)", name = "paperState")
    @TableField("paper_state")
    private Integer paperState;

    @ApiModelProperty(value = "试题顺序", name = "questionSeq")
    @TableField("question_seq")
    private Integer questionSeq;

    @ApiModelProperty(value = "试题分数", name = "questionScore")
    @TableField("question_score")
    private Integer questionScore;

    @ApiModelProperty(value = "试题选项编号", name = "answerId")
    @TableField("answer_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long answerId;

    @ApiModelProperty(value = "试题选项排序", name = "answerSeq")
    @TableField("answer_seq")
    private Integer answerSeq;

    @ApiModelProperty(value = "用户编号", name = "userId")
    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户名称", name = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户答案", name = "answer")
    private String answer;

    @ApiModelProperty(value = "用户得分", name = "score")
    private Integer score;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;


    @ApiModelProperty(value = "第几次考试", name = "examNum")
    @TableField("exam_num")
    private Integer examNum = 1;


    @ApiModelProperty(value = "计划课程关联id", name = "id")
    @TableField("plan_course_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planCourseId;

}
