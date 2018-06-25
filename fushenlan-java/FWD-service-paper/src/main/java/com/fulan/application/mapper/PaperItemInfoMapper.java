package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.paper.domain.er.PaperItemInfo;
@Mapper
public interface PaperItemInfoMapper extends BaseMapper<PaperItemInfo> {
	int  deletePaperItemVoByPaperId(Long id);
}
