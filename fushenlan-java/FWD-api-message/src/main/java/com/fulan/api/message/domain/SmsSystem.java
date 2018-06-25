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
@Api(tags = "SmsSystem", description = "短信业务系统")
@TableName("sms_system")
public class SmsSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信业务系统编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "短信业务系统编号码", name = "code")
    private String code;

    @ApiModelProperty(value = "短信业务系统名称", name = "name")
    private String name;

}