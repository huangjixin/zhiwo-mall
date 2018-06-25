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


    @ApiModelProperty(value = "系统编号", name = "id")
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

    @ApiModelProperty(value = "试题分值", name = "questionScore")
    @TableField("question_score")
    private Integer questionScore;

    @ApiModelProperty(value = "试题排序", name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;


}
