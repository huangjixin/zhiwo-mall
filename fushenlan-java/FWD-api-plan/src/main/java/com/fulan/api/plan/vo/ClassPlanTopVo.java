package com.fulan.api.plan.vo;

import lombok.Data;

import java.util.List;

@Data
public class ClassPlanTopVo {

    private Integer isFinished;

    private String className;

    private List<ClassPlanVo> classPlanVos;
}
