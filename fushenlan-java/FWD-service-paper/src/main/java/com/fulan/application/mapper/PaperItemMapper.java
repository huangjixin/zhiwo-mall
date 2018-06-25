package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.fulan.api.paper.domain.er.PaperItem;
import com.fulan.api.paper.domain.er.PaperItem;
import com.fulan.api.paper.vo.PaperInfoVo;

@Mapper
public interface PaperItemMapper extends BaseMapper<PaperItem>{

	List<PaperInfoVo> selectByRecord(Map map);
}
