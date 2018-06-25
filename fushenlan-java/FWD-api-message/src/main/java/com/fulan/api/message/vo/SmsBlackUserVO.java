package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsBlackUserVO", description = "短信黑名单")
public class SmsBlackUserVO extends SmsPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "类型", name = "type")
    private String type;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

}