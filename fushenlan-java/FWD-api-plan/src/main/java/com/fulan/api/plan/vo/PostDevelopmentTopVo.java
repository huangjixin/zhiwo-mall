package com.fulan.api.plan.vo;

import lombok.Data;

import java.util.List;

@Data
public class PostDevelopmentTopVo {


    private Integer isFinished;

    private List<PostDevelopmentVo> postDevelopmentVos;
}
