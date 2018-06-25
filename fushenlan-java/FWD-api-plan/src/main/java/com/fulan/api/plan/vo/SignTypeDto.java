package com.fulan.api.plan.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by fsl on 2018/2/1.
 */

@Data
@Api(tags = "SignTypeDto", description = "签到类型")
public class SignTypeDto implements Serializable {

    @ApiModelProperty(value = "签到类型- 是否需要报名，1需要，0不需要",name="isNeedEnter")
    private Integer isNeedEnter;

    @ApiModelProperty(value = "签到类型- 是否需要签到，1需要，0不需要",name="isNeedSign")
    private Integer isNeedSign;

    @ApiModelProperty(value = "签到类型-不需要报名也能签到，1能，0不能",name="isNoneedEnter")
    private Integer isNoneedEnter;
}
