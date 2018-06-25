package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

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
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long organization;

//    @ApiModelProperty(value = "权限类型(1指定分类，2指定用户，3上传名单)", name = "authorityType")
    private Integer authorityType;

//    @ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)", name = "authorityId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long authorityId;

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public Integer getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(Integer authorityType) {
        this.authorityType = authorityType;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
