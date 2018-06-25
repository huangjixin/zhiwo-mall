package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ExamPaperVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperQuestionMapper extends BaseMapper<PaperQuestion> {
    Integer deleBatchHostId(List<Long> list);
}
