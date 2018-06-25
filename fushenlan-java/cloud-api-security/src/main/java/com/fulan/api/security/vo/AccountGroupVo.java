package com.fulan.api.security.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

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
public class AccountGroupVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "系统编号",name="id")
    @IdAnnon
	private Long id;

    @ApiModelProperty(value = "用户编号",name="accountId")
	@TableField("account_id")
	private Long accountId;

    @ApiModelProperty(value = "组编号",name="groupId")
	@TableField("group_id")
	private Long groupId;
    
    private String groupName;  //用户组名称
    
    private String accountName; //用户名称

}
