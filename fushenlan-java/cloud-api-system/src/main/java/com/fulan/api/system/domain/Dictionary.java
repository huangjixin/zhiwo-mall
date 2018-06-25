package com.fulan.api.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 数据字典实体
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Data
@ApiModel(value="字典对象",description="字典对象")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value="主键",name="id",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
   	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;
    /**
     * 字典code
     */
    @ApiModelProperty(value="字典code",name="code",example="abc")
	private String code;
    /**
     * 中文名称
     */
    @ApiModelProperty(value="中文名称",name="cnName",example="颜色")
	private String cnName;
    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称",name="enName",example="head")
	private String enName;
    /**
     * 字典取值
     */
    @ApiModelProperty(value="字典取值",name="value",example="颜色")
	private String value;
    /**
     * 根id（当前为顶层则为“0”）
     */
    @ApiModelProperty(value="根id",name="rootId",example="0")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long rootId;
    /**
     * 父字典的id（当前为顶层为“0”）
     */
    @ApiModelProperty(value="父字典的id",name="parentId",example="0")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long parentId;
    /**
     * 节点层级（当前为顶层则为“0”）
     */
    @ApiModelProperty(value="节点层级",name="layer",example="0")
	private Integer layer;
    /**
     * 同层级排序（从1开始）
     */
    @ApiModelProperty(value="同层级排序",name="sort",example="1")
	private Integer sort;
    /**
     * 字典描述
     */
    @ApiModelProperty(value="字典描述",name="description",example="颜色")
	private String description;
    /**
     * 是否可用：0：不可用，1：可用
     */
    @ApiModelProperty(value="是否可用",name="enabled",example="0")
	private Boolean enabled;
    /**
     * 字典类型：0：系统字典，1：业务选项
     */
    @ApiModelProperty(value="字典类型",name="systemFlag",example="0")
	private Integer systemFlag;
    /**
     * 创建者id
     */
    @ApiModelProperty(value="创建者id",name="createById",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createById;
    /**
     * 创建者名称
     */
    @ApiModelProperty(value="创建者名称",name="createByName",example="张三")
	private String createByName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-01-18 09:27:25")
	private Date createTime;
    /**
     * 更新者id
     */
    @ApiModelProperty(value="更新者id",name="updateById",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long updateById;
    /**
     * 修改者名称
     */
    @ApiModelProperty(value="修改者名称",name="updateByName",example="李四")
	private String updateByName;
    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间",name="updateTime",example="2018-01-18 09:27:25")
	private Date updateTime;

}
