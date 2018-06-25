package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanComment;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 评论的详细
 * </p>
 *
 * @author Hedge
 * @since 2018-02-01
 */
@Data
public class PlanCommentDto extends PlanComment implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "点赞数",name="likeNum")
    private Integer likeNum;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "点赞Id",name="likeId")
    private Long likeId;
}
