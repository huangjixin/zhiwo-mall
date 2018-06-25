package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsTemplateVO", description = "短信模板")
public class SmsTemplateVO extends SmsPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码", name = "code")
    private String code;

    @ApiModelProperty(value = "标题", name = "title")
    private String title;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "大类类型", name = "type")
    private String type;

    @ApiModelProperty(value = "参数类型", name = "parameterType")
    private String parameterType;

}