package com.fulan.api.material.vo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fulan.api.material.domain.Material;
import io.swagger.annotations.Api;
import lombok.Data;


/**
 * <p>
 * 资料与附件地址
 * </p>
 *
 * @author Hedge
 * @since 2018-03-22
 */
@Data
@Api(tags = "MaterialAttDto", description = "资料与附件地址")
public class MaterialAttachDto extends Material {

    private String filePath;
}
