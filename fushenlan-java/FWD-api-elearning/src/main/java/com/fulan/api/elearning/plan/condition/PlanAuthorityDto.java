package com.fulan.api.elearning.plan.condition;

import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 课程权限数据对象
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
//@Api(tags = "PlanAuthorityDto", description = "课程权限数据对象")
public class PlanAuthorityDto implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "机构", name = "organization")
    private Long organization;

//    @ApiModelProperty(value = "权限类型(1指定分类，2指定用户，3上传名单)", name = "authorityType")
    private Integer authorityType;

//    @ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)", name = "authorityId")
    private Long authorityId;

}
