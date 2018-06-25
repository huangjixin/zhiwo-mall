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
 * 试卷试题关联表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PaperQuestion", description = "试卷试题关联表")
@TableName("el_paper_question")

public class PaperQuestion implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "系统编号",name="id")
	private Long id;

@ApiModelProperty(value = "试卷编号",name="paperId")
	@TableField("paper_id")
	private Long paperId;

@ApiModelProperty(value = "试题编号",name="questionId")
	@TableField("question_id")
	private Long questionId;

@ApiModelProperty(value = "试题分值",name="questionScore")
	@TableField("question_score")
	private Integer questionScore;

@ApiModelProperty(value = "试题排序",name="seq")
	private Integer seq;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
