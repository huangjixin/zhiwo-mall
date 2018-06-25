package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 课程咨询的详细
 * </p>
 *
 * @author Hedge
 * @since 2018-02-02
 */
@Data
public class PlanQuestionDto extends PlanQuestion implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "点赞数",name="likeNum")
    private Integer likeNum;
    @ApiModelProperty(value = "点赞Id",name="likeId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long likeId;
}
