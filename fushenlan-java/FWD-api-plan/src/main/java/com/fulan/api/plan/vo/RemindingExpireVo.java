package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Api(tags = "RemindingExpireVo", description = "即将过期视图类")
public class RemindingExpireVo implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "计划ID",name="planId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Integer planId;

    @ApiModelProperty(value = "时间类型 1今天2明天3后天",name="timeType")
    private Integer timeType;

    @ApiModelProperty(value = "时间戳",name="timeStamp")
    private String timeStamp;

    @ApiModelProperty(value = "计划/活动名称",name="name")
    private List<CourseVo> courseVos;

}
