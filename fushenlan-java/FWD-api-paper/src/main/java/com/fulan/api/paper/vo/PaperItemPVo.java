package com.fulan.api.paper.vo;

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
 * 试卷题目
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PaperItem", description = "试卷题目")
@TableName("er_paper_item")

public class PaperItemPVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "考卷id", name = "paperId")
    @TableField("paper_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long paperId;

    @ApiModelProperty(value = "考题名称", name = "paperItemName")
    @TableField("paper_item_name")
    private String paperItemName;

    @ApiModelProperty(value = "考题说明", name = "paperItemDesc")
    @TableField("paper_item_desc")
    private String paperItemDesc;

    @ApiModelProperty(value = "总分", name = "totalScore")
    @TableField("total_score")
    private Integer totalScore;

    @ApiModelProperty(value = "预警分", name = "warningScore")
    @TableField("warning_score")
    private Integer warningScore;

    @ApiModelProperty(value = "排序", name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "1:基础题。2：附加题", name = "paperType")
    @TableField("paper_type")
    private Integer paperType;

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

    private int score;

}
