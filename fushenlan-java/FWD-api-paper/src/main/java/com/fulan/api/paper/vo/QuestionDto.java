package com.fulan.api.paper.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String  singleChoiceVos;

    private String multipleChoiceVos;

    private String judgeVos;

    private String shortAnswers;

    private List<ShortAnswerVo> shortList;

    private Long planCourseId;

    private Long paperId;

}
