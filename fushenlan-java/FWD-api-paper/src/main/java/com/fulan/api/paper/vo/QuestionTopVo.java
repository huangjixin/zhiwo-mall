package com.fulan.api.paper.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionTopVo {

    private String questionTitle;

    private Integer questionType;

    private Integer questionTotalScore;

    private Integer questionTotalNum;

    private List<QuestionVo> questions = new ArrayList<>();
}
