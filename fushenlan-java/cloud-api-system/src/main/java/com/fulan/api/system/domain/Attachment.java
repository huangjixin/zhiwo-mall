package com.fulan.api.system.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="附件对象",description="附件对象")
public class Attachment {
	 /**
	     * 主键id
	     */
    @ApiModelProperty(value="主键",name="id",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
   	@JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    @ApiModelProperty(value="关联表名称",name="tableName",example="1")
    private String tableName;
    @ApiModelProperty(value="关联编号",name="hostId",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
    private Long hostId;
    @ApiModelProperty(value="附件名称-系统命名",name="name",example="1")
    private String name;
    @ApiModelProperty(value="附件大小",name="size",example="1")
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
    private Long size;
   
    private String path;
   
    private String description;
   
    private String fileExt;
    
    private String originalName;
   
    private Integer seq;
   
    private Integer type;

    @ApiModelProperty(value="附件类型",name="category",example="1")
    private Integer category;

    private String url;

    private Integer state;
    @ApiModelProperty(value="是否删除",name="isDelete",example="1")
    private Integer isDelete;
    @JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gmtCreate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date gmtModified;

   
    
}