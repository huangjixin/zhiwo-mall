package com.fulan.api.material.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

/**
 * <p>
 * 资料表，讲师，管理员共用
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Material", description = "资料表，讲师，管理员共用")
@TableName("el_material")
public class MaterialVoCMS implements Serializable {

   private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "资料编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "资料名称", name = "name")
    private String name;

    @ApiModelProperty(value = "资料类型(视频/SCORM/PPT/WORD/EXCEL)", name = "type")
    private Integer type;

    @ApiModelProperty(value = "资料说明", name = "description")
    private String description;

    @ApiModelProperty(value = "一级分类(用户组)", name = "groupId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long groupId;

    @ApiModelProperty(value = "二级分类(用户组下面的标签)", name = "tagId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(value = "是否分享(1 是， 0 否)", name = "isShare")
    private Integer isShare;

    @ApiModelProperty(value = "是否关联计划(1 是， 0 否)", name = "isRelatePlan")
    private Integer isRelatePlan;

    @ApiModelProperty(value = "提交者", name = "createUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "上传时间", name = "gmtCreate")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    private Date gmtModified;
    
    private String groupName;  //一级分类，用户组名称
    
    private String tagName;    //二级分类，用户组名称

}
