package com.fulan.api.material.vo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by fsl on 2018/1/20.
 */
@Data
@Api(tags = "Material", description = "资料表，讲师，管理员共用")
@TableName("el_material")
public class MaterialVO implements Serializable {

    @ApiModelProperty(value = "是否分享",name="isShare")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "关键字",name="keyword")
    private String keyword;

    @ApiModelProperty(value = "资料类型",name="type")
    private Integer type;

    @ApiModelProperty(value = "二级分类",name="tagId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(value = "开始时间",name="startTime")
    private Date startTime;

    @ApiModelProperty(value = "结束时间",name="endTime")
    private Date endTime;

    @ApiModelProperty(value = "资料批量删除传递值",name="listM")
    List<MaterialDeleVO> listM;



}
