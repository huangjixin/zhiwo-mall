package com.fulan.api.security.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
@Data
public class UserGroupVo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "系统编号",name="id")
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "用户组名称",name="groupName")
    private String groupName;

    @ApiModelProperty(value = "用户组说明",name="groupDesc")
    private String groupDesc;

    @ApiModelProperty(value = "创建人",name="createUser")
    private Long createUser;

    @ApiModelProperty(value = "创建时间",name="createTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改人",name="modifyUser")
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间",name="modifyTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;
    
    
    private Integer userNum;  //用户组下用户数量
    
    private String strTagIds;   //关联的标签字符串
    
    private String strUserIds; //关联的用户Id字符串
    
}
