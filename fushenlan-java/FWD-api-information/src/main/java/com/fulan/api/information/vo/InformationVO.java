package com.fulan.api.information.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by fsl on 2018/4/9.
 */
@Data
public class InformationVO {


    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long informationId;
    @ApiModelProperty(value = "标题", name = "content")
    private String content;

    @ApiModelProperty(value = "阅读量", name = "readCount")
    private Integer readCount;

    @ApiModelProperty(value = "发布时间", name = "issueTime")
    private Date issueTime;

    @ApiModelProperty(value = "报名总数", name = "signUpCount")
    private Integer signUpCount;

    @ApiModelProperty(value = "已经报名人数", name = "hasSignedUpPerson")
    private Integer hasSignedUpPerson;

    @ApiModelProperty(value = "1已经报名0未报名", name = "hasSignedUp")
    private Integer hasSignedUp;

    @ApiModelProperty(value = "资讯类型", name = "infoNewsType")
    private Integer infoNewsType;

    @ApiModelProperty(value = "路径", name = "infoPath")
    private String infoPath;




}
