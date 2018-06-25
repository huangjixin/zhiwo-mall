package com.fulan.application.mapper;

import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ExamAccountVo;
import com.fulan.api.paper.vo.ExamPaperVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperExamMapper {

    /**
     * 根据试卷ID获取试卷
     * @param paperId
     * @return
     */
    public ExamPaperVo getPaperExam(Long paperId);

    /**
     *
     * @param map
     * @return
     */
    public List<UserExam> getUserExam(Map<String, Object> map);
    
    public List<ExamAccountVo> selectExamAccount( @Param("userName")String userName, @Param("questionType") String questionType, @Param("id")String id);
    
    
    public List<ExamAccountVo> selectExamAccountSearch( @Param("userName")String userName);

    /**
     * 获取试卷解析
     * @param map
     * @return
     */
    public ExamPaperVo getAnalysis(Map<String,Object> map);

    /**
     * 获取用户考试得分
     * @param map
     * @return
     */
    public Integer getScore(Map<String,Object> map);

    /**
     * 获取 用户考试次数
     * @param map
     * @return
     */
    public Integer getExamNum(Map<String,Object> map);

    /**
     * 获取用户已经考试的次数
     * @param map
     * @return
     */
    public Integer getMaxExamNum(Map<String,Object> map);


}
