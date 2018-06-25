package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(tags = "ActivityCollectVo", description = "收藏线下活动")
public class ActivityCollectVo extends OfflineActivityVO{

    @ApiModelProperty(value = "收藏ID", name = "collectId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long collectId;

    @ApiModelProperty(value = "是否可以报名1：可以，0：不可以", name = "hasAuthority")
    private Integer hasAuthority;
}
