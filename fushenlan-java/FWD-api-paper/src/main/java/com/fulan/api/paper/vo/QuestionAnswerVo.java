package com.fulan.api.paper.vo;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.models.auth.In;
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
@Api(tags = "QuestionAnswerVo", description = "试题答案表")
public class QuestionAnswerVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户答案",name="userAnswer")
    private String userAnswer;

    @ApiModelProperty(value = "选项",name="option")
    private String option;

    @ApiModelProperty(value = "得分",name="score")
    private Integer score;

}
