package com.fulan.api.plan.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程评论点赞表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-25
 */
@Data
@Api(tags = "Like", description = "课程评论点赞表")
@TableName("el_like")

public class Like implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "计划编号", name = "planId")
    @TableField("plan_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "点赞类型  1评论，2问答", name = "type")
    private Integer type;

    @ApiModelProperty(value = "评论或者问答的编号", name = "hostId")
    @TableField("host_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long hostId;

    @ApiModelProperty(value = "点赞人", name = "userId")
    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "点赞人名称", name = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;


}
