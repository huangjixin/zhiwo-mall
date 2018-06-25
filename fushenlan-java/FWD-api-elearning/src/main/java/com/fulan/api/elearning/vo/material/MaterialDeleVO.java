package com.fulan.api.elearning.vo.material;

import com.baomidou.mybatisplus.annotations.TableName;
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
    private Long ids;

}
