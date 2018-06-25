package com.fulan.api.agent.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName:VTag
 * Date:     2018-04-10 16:47
 * Reason:   针对IRIS接口做添加标签用的
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class VTag implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tagId;


    @ApiModelProperty(
            value = "代理人编号",
            name = "agentCode")
    private String agentCode;

    @ApiModelProperty(
            value = "标签名称",
            name = "name")
    private String name;

    @ApiModelProperty(
            value = "规则",
            name = "rule",
            example = "{'minAge':'1','maxAge':'2','sex':['M'],'categoryCode':['10001'],'planCode':['10001']}")
    private String rule;

    private String nameDesc;

    private String ruleDesc;

    private Integer fillera;

    private String fillerb;



}