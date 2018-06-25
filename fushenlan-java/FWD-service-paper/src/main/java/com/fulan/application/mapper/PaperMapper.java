package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
@Mapper
public interface PaperMapper extends BaseMapper<Paper>{
	
	List<PaperManageVo> paperManageSerch(RowBounds rowBounds, @Param("keyWord")String keyWord,  @Param("orgId")String orgId, 
			   @Param("pageNo")int pageNo, 
			   @Param("pageSize")int pageSize);

	PaperVo paperCheckById(Paper paper);
	
	int paperManageSerchCount(Map<String,Object> map); 
	
	int deletePaperVo(String paperId);
	
	/**
	 * 在线增员-查询代理人历史答题详情
	 * */
	List<PaperDetailVo> selectPaperDetailList(Map<String,Object> map);
	/**
	 * 在线增员-试题详情
	 * */
	List<PaperVo> getPaperByPaperType(Map map);
	
	
	
	
}
