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

/**
 * Created by fsl on 2018/1/22.
 */
@Data
@Api(tags = "MaterialDeleVO", description = "资料删除参数")
@TableName("materialDeleVO")
public class MaterialDeleVO implements Serializable {

    @ApiModelProperty(value = "是否分享",name="isShare")
    private Integer isShare;

    @ApiModelProperty(value = "删除id",name="ids")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long ids;

}
