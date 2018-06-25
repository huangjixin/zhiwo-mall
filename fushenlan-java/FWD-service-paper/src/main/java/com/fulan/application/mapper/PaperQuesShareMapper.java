package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.paper.domain.el.PaperQuesShare;

@Mapper
public interface PaperQuesShareMapper extends BaseMapper<PaperQuesShare> {
	
	Integer saveBatch(Map<String,Object> map);
	
	Integer deleBatchIds(List<Long> list);

	List<Long> selectShareQuestion(Long idl);

	List<Long> selectSharePaper(Long idl);

}
