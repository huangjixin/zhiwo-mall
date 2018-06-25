package com.fulan.api.plan.vo;

import lombok.Data;

import java.util.List;

@Data
public class CompulsoryPlanTopVo {

    private Integer isFinished;

    private List<CompulsoryPlanVo> compulsoryPlanVos;

}
