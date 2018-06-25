package com.fulan.api.message.domain;

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

@Data
@Api(tags = "SmsParameterFactor", description = "短信参数因子")
@TableName("sms_parameter_factor")
public class SmsParameterFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信消息编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "因子", name = "factor")
    private String factor;

    @ApiModelProperty(value = "因子名称", name = "factorName")
    private String factorName;

    @ApiModelProperty(value = "因子类型", name = "factorType")
    private String factorType;

    @ApiModelProperty(value = "说明", name = "remark")
    private String remark;

}