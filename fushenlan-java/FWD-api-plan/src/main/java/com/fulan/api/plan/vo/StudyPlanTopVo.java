package com.fulan.api.plan.vo;

import io.swagger.annotations.Api;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Api(tags = "StudyPlanTopVo", description = "我的计划视图类")
public class StudyPlanTopVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String planType;//计划类型

    private List<StudyPlanVo> studyPlanVos;


}
