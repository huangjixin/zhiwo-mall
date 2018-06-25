package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by fsl on 2018/1/24.
 */
@Data
@Api(tags = "ActivityVO", description = "线下活动参数")
public class ActivityVO {

    @ApiModelProperty(value = "活动id", name = "activityId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long activityId;//活动id

    @ApiModelProperty(value = "用户id", name = "userId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;//用户id

    @ApiModelProperty(value = "课程id", name = "courseId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;//课程id

    @ApiModelProperty(value = "当前页数", name = "currentPage")
    protected Integer currentPage = 1;

    @ApiModelProperty(value = "显示多少条", name = "pageSize")
    protected Integer pageSize = 5;

}
