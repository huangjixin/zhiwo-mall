package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 公开课请求数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Data
public class PlanRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "tagId", value = "一级分类")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(name = "childTagId", value = "二级分类")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long childTagId;

    @ApiModelProperty(name = "name", value = "名字搜索")
    private String name;

    @ApiModelProperty(name = "pageNo", value = "要跳转的页数")
    private int pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数，默认：10")
    private int pageSize;

    @ApiModelProperty(name = "pageSortFiled", value = "排序字段名，默认：viewsNum")
    private String pageSortFiled;

    @ApiModelProperty(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：desc")
    private  String pageSortType;

    @ApiModelProperty(name = "planId", value = "计划Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(name = "courseId", value = "课程Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private  Long courseId;

    @ApiModelProperty(name = "planType", value = "计划类型-1公开课/2学习计划/3班级计划/4岗位发展/5必修任务")
    private  Integer planType;

    @ApiModelProperty(name = "isSeq", value = "是否需要按顺序学习")
    private  Integer isSeq;



}
