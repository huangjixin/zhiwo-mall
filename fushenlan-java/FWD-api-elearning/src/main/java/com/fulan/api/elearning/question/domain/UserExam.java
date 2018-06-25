package com.fulan.api.elearning.question.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

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


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "试卷编号",name="paperId")
	@TableField("paper_id")
	private Long paperId;

@ApiModelProperty(value = "试题编号",name="questionId")
	@TableField("question_id")
	private Long questionId;

@ApiModelProperty(value = "试题类型(单选/多选)",name="questionType")
	@TableField("question_type")
	private Integer questionType;

@ApiModelProperty(value = "试题顺序",name="questionSeq")
	@TableField("question_seq")
	private Integer questionSeq;

@ApiModelProperty(value = "试题分数",name="questionScore")
	@TableField("question_score")
	private Integer questionScore;

@ApiModelProperty(value = "试题选项编号",name="answerId")
	@TableField("answer_id")
	private Long answerId;

@ApiModelProperty(value = "试题选项排序",name="answerSeq")
	@TableField("answer_seq")
	private Integer answerSeq;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;

@ApiModelProperty(value = "用户名称",name="userName")
	@TableField("user_name")
	private String userName;

@ApiModelProperty(value = "用户答案",name="answer")
	private String answer;

@ApiModelProperty(value = "用户得分",name="score")
	private Integer score;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
