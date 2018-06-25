package com.fulan.api.paper.vo;


import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Api(tags = "ExamAccountVo", description = "试卷")
public class ExamAccountVo implements Serializable {

    @ApiModelProperty(value = "编号", name = "id")
    private Long id;

    @ApiModelProperty(value = "试卷编号", name = "paperId")
    private Long paperId;

    @ApiModelProperty(value = "试题编号", name = "questionId")
    private Long questionId;

    @ApiModelProperty(value = "试题类型(单选/多选)", name = "questionType")
    private Integer questionType;

    @ApiModelProperty(value = "试题状态(1已阅卷/2待阅卷)", name = "paperState")
    private Integer paperState;

    @ApiModelProperty(value = "试题顺序", name = "questionSeq")
    private Integer questionSeq;

    @ApiModelProperty(value = "试题分数", name = "questionScore")
    private Integer questionScore;

    @ApiModelProperty(value = "试题选项编号", name = "answerId")
    private Long answerId;

    @ApiModelProperty(value = "试题选项排序", name = "answerSeq")
    private Integer answerSeq;

    @ApiModelProperty(value = "用户编号", name = "userId")
    private Long userId;

    @ApiModelProperty(value = "用户名称", name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户答案", name = "answer")
    private String answer;

    @ApiModelProperty(value = "用户得分", name = "score")
    private Integer score;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    private Date gmtModified;
    
    @ApiModelProperty(value = "职级", name = "postType")
    private String postType;
    
    @ApiModelProperty(value = "公司code值", name = "cnName")
    private String cnName;
    
    @ApiModelProperty(value = "是否通过", name = "pass")
    private String pass;
    
    @ApiModelProperty(value = "通过分数", name = "passMark")
    private Integer passMark;
    
    @ApiModelProperty(value = "試卷满分", name = "fullMark")
    private Integer fullMark;
    
    @ApiModelProperty(value = "試卷名称", name = "name")
    private String name;
    
    @ApiModelProperty(value = "试卷答题时间", name = "examTime")
    private Integer examTime;
    
    @ApiModelProperty(value = "试题内容", name = "content")
    private String content;
    
    
    
    
    
}
