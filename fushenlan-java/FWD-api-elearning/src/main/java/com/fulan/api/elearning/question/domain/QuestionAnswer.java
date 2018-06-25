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
 * 试题答案表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "QuestionAnswer", description = "试题答案表")
@TableName("el_question_answer")

public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;


	private Long id;

	@TableField("question_id")
	private Long questionId;

@ApiModelProperty(value = "1单选/2多选/3判断/4问答",name="questionType")
	@TableField("question_type")
	private Integer questionType;

	private String answer;

@ApiModelProperty(value = "1 表示是， 0 表示否",name="isRight")
	@TableField("is_right")
	private Integer isRight;

	private Integer seq;

	@TableField("create_user")
	private Long createUser;

	@TableField("gmt_create")
	private Date gmtCreate;

	@TableField("modify_user")
	private Long modifyUser;

	@TableField("gmt_modified")
	private Date gmtModified;



}
