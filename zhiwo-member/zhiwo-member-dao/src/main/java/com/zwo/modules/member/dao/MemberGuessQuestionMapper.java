package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberGuessQuestion;
import com.zwo.modules.member.domain.MemberGuessQuestionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberGuessQuestionMapper extends Mapper<MemberGuessQuestion> {
    int countByExample(MemberGuessQuestionCriteria example);

    int deleteByExample(MemberGuessQuestionCriteria example);

    List<MemberGuessQuestion> selectByExample(MemberGuessQuestionCriteria example);

    int updateByExampleSelective(@Param("record") MemberGuessQuestion record, @Param("example") MemberGuessQuestionCriteria example);

    int updateByExample(@Param("record") MemberGuessQuestion record, @Param("example") MemberGuessQuestionCriteria example);
}