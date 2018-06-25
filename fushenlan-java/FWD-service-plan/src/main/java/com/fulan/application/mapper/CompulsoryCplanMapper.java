package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;

@Mapper
public interface CompulsoryCplanMapper extends BaseMapper<CompulsoryCplan>{
     
	
	List<CompulsoryCplanVo> compulsoryCplanSerch(RowBounds rowBounds,@Param("name")String name,
			   @Param("tagId")String tagId,
			   @Param("state")String state, 
			   @Param("pageNo")int pageNo, 
			   @Param("pageSize")int pageSize);
	
	int compulsoryCplanSerchCount( @Param("name")String name,
			                       @Param("tagId")String tagId,
			                       @Param("state")String state
			                      );
	
	int deleteByPrimaryKey(String id);
	
	CompulsoryCplan selectOne(String id);
	
	int updateByPrimaryKeySelective (CompulsoryCplan compulsoryCplan);
	
	int updateByPrimaryKeySelective1 (CompulsoryCplan compulsoryCplan);
	
	int insertCompulsoryCplan(@RequestBody CompulsoryCplan compulsoryCplan);
	
	CompulsoryCplanManageVo selectById(@Param("id") Long id);
}