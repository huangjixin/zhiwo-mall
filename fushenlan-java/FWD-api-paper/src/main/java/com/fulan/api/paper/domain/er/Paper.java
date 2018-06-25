package com.fulan.api.paper.domain.er;

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
 * 试卷
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Paper", description = "试卷")
@TableName("er_paper")

public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "试卷名称", name = "paperName")
    @TableField("paper_name")
    private String paperName;

    @ApiModelProperty(value = "试卷类型(1.初审，2:甄选，3：决定)", name = "paperType")
    @TableField("paper_type")
    private Integer paperType;

    @ApiModelProperty(value = "试卷说明", name = "paperDesc")
    @TableField("paper_desc")
    private String paperDesc;

    @ApiModelProperty(value = "所属分公司", name = "orgId")
    @TableField("org_id")
    private String orgId;

    @ApiModelProperty(value = "排序", name = "seq")
    private Integer seq;
    
    @ApiModelProperty(value = "是否是总公司",name="headFlag")
	private String headFlag;

    @ApiModelProperty(value = "", name = "createTime")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "", name = "createUser")
    @TableField("create_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "", name = "updateTime")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "", name = "updateUser")
    @TableField("update_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long updateUser;

}
