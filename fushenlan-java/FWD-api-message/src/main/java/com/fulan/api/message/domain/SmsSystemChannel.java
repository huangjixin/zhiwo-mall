package com.fulan.api.message.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Api(tags = "SmsSystemChannel", description = "短信业务系统通道关联")
public class SmsSystemChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统集合", name = "systems")
    private List<SmsSystem> systems;

    @ApiModelProperty(value = "通道集合", name = "channels")
    private List<SmsChannel> channels;

}