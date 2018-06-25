package com.fulan.api.paper.vo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Api(tags = "ExamPaperVo", description = "试卷")
public class ExamPaperVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷编号",name="id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "试卷名称",name="name")
    private String name;

    @ApiModelProperty(value = "试卷说明",name="discription")
    private String discription;

    @ApiModelProperty(value = "职业规范/思维方法/演讲口才/销售技巧/保险产品",name="type")
    private Integer type;

    @ApiModelProperty(value = "答题时间",name="examTime")
    private Integer examTime;

    @ApiModelProperty(value = "试卷满分",name="fullMark")
    private Integer fullMark;

    @ApiModelProperty(value = "通过分数",name="passMark")
    private Integer passMark;

    @ApiModelProperty(value = "可测试次数",name="testNum")
    private Integer testNum;

    @ApiModelProperty(value = "是否人工阅卷,1是，0否",name="isArtificialMark")
    private Integer isArtificialMark;

    @ApiModelProperty(value = "是否随机选题,1是，0否",name="isArtificialMark")
    private Integer isRandom;

    @ApiModelProperty(value = "单选题总分",name="singleTotalScore")
    private Integer singleTotalScore=0;

    @ApiModelProperty(value = "多选题总分",name="multipleTotalScore")
    private Integer multipleTotalScore=0;

    @ApiModelProperty(value = "判断题总分",name="judgeTotalScore")
    private Integer judgeTotalScore=0;

    @ApiModelProperty(value = "简答题总分",name="shortTotalScore")
    private Integer shortTotalScore=0;

    @ApiModelProperty(value = "用户单选题总分",name="singleUserScore")
    private Integer singleUserScore=0;

    @ApiModelProperty(value = "用户多选题总分",name="multipleUserScore")
    private Integer multipleUserScore=0;

    @ApiModelProperty(value = "用户判断题总分",name="judgeUserScore")
    private Integer judgeUserScore=0;

    @ApiModelProperty(value = "用户简答题总分",name="shortUserScore")
    private Integer shortUserScore=0;

    @ApiModelProperty(value = "用户考试得分",name="userScore")
    private Integer userScore;//用户考试得分

    @ApiModelProperty(value = "是否通过考试",name="isPassExam")
    private Integer isPassExam;//是否通过考试

    @ApiModelProperty(value = "是否考试最大次数",name="isMaxExam")
    private Integer isMaxExam;//是否通过考试

    private List<QuestionVo> questions;


    private List<QuestionTopVo> questionTopVos;

}
