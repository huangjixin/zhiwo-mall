package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.vo.QuestionVo;

@Mapper
public interface QuestionMapper extends BaseMapper<Question>{
	
	List<Question> listForManage(Page<Question> page,@Param("type")String type,@Param("pubType")String pubType,@Param("submitter")String submitter, @Param("createUser")String createUser,@Param("content") String content,
			@Param("groupId") String groupId,@Param("tagId") String tagId,
			  int pageNo, int pageSize);
	
	List<Question> listForOtherManage(@Param("type")String type,@Param("tagId") String tagId,@Param("keyWord") String keyWord,@Param("createUser") String createUser);
	
	Integer listForManageCount(@Param("type")String type,@Param("pubType")String pubType,@Param("submitter")String submitter, @Param("createUser")String createUser,@Param("content") String content,
			@Param("groupId") String groupId,@Param("tagId") String tagId);
	
	List<Question> seleRandomForManage(Map<String,Object> map);
	
	QuestionVo questionAndAnswer(String id);
	
	Integer deleById(Long id);
	
	Integer deleBatchIds(List<Long> list);
	
	List<Map<String,String>> isNotRelation(List<Long> list);
	
	Integer saveQuestion(QuestionVo questionVo);
	
	Integer updateQuestion(QuestionVo questionVo);
	
	List<QuestionVo> seleByIdList(@Param("idList") List<Long> idList);
	
	Integer updateByIdList(@Param("idList")List<Long> idList,@Param("isShare")String isShare);

	List<String> selectPaperName(List<Long> idList);
}
