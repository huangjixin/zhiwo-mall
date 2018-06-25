package com.fulan.api.system.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 组织机构实体
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Data
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value="主键",name="id",example="1")
	private String id;
    /**
     * 机构代码
     */
    @ApiModelProperty(value="机构代码",name="code",example="abc")
	private String code;
	/**
	 * 机构名称
	 */
    @ApiModelProperty(value="机构名称",name="cnName",example="名称")
	private String cnName;
	/**
	 * 外文名称
	 */
    @ApiModelProperty(value="外文名称",name="enName",example="abc")
	private String enName;
    /**
     * 顶层机构id（当前为顶层则为“0”）
     */
    @ApiModelProperty(value="顶层机构id",name="rootId",example="0")
    @JsonSerialize(using = LongJsonSerializer.class)
   	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long rootId;
    /**
     * 父机构id（当前为顶层则为“0”）
     */
    @ApiModelProperty(value="父机构id",name="parentId",example="0")
	private String parentId;
    /**
     * 企业id
     */
    @ApiModelProperty(value="企业id",name="companyId",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
   	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long companyId;
    /**
     * 机构地址
     */
    @ApiModelProperty(value="机构地址",name="address",example="上海")
	private String address;
    /**
     * 机构类型：1:总公司;2:分公司;3:地市;4:区县;5:营业点（按需扩展）
     */
    @ApiModelProperty(value="机构类型",name="type",example="1")
	private Integer type;
    /**
     * 是否可用
     */
    @ApiModelProperty(value="是否可用",name="enabled",example="0")
	private Boolean enabled;
    /**
     * 邮编
     */
    @ApiModelProperty(value="邮编",name="postCode",example="010508")
	private String postCode;
    /**
     * 电话号码
     */
    @ApiModelProperty(value="电话号码",name="phoneNumber",example="136533333333")
	private String phoneNumber;
    /**
     * 传真
     */
    @ApiModelProperty(value="传真",name="faxNumber",example="2131309")
	private String faxNumber;
    /**
     * 层级
     */
    @ApiModelProperty(value="层级",name="layer",example="0")
	private Integer layer;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序",name="sort",example="desc")
	private Integer sort;
    /**
     * 机构描述
     */
    @ApiModelProperty(value="机构描述",name="description",example="总部")
	private String description;
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
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
    @ApiModelProperty(value="修改者名称",name="updateByName",example="名称")
	private String updateByName;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value="修改时间",name="updateTime",example="2018-01-18 09:27:25")
	private Date updateTime;
    
    

}
