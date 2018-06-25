package com.fulan.api.plan.vo;

import com.fulan.api.plan.domain.ClassPlan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 班级计划数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-02-06
 */
@Data
public class ClassPlanDto extends ClassPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "已报名人数",name="enterNum")
    private Integer enterNum;


    @ApiModelProperty(value = "是否已报名，1：已报名，0：未报名",name="enterFlag")
    private Integer enterFlag;
}
