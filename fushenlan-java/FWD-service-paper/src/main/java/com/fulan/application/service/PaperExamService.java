package com.fulan.application.service;

import java.util.List;

import com.fulan.api.paper.vo.ExamAccountVo;
import com.fulan.api.paper.vo.ExamPaperVo;
import com.fulan.api.paper.vo.QuestionDto;

/**
 * 测试service
 * yangzexu
 */
public interface PaperExamService {

    /**
     * 根据试卷ID获取试卷
     * @param paperId
     * @return
     */
    public ExamPaperVo getPaperExam(Long paperId);

    /**
     * 试卷提交
     * @param question
     * @return
     */
    public ExamPaperVo submit(QuestionDto question);

    /**
     * 获取试卷解析
     * @param paperId
     * @param planCourseId
     * @return
     */
    public ExamPaperVo getAnalysis(Long paperId,Long planCourseId);
    
    public List<ExamAccountVo> getExamAccountVo(String userName,String questionType,String id);
    
    public List<ExamAccountVo> getExamAccountOtherVo(String userName);

}
