package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PublicClassCollectVo extends PublicCourseDto{

    @ApiModelProperty(value = "收藏编号",name="collectId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long collectId;

    @ApiModelProperty(value = "完成进度",name="learningProgress")
    private BigDecimal learningProgress;

    @ApiModelProperty(value = "课程类型",name="courseTypes")
    private List<String> courseTypes;

}
