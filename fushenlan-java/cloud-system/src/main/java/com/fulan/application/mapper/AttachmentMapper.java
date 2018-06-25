package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.domain.Attachment;


public interface AttachmentMapper extends BaseMapper<Attachment>{
    Integer insert(Attachment record);
    
    @Select("SELECT * from attachment where id=#{id} and is_delete=1")
	Attachment selectByIdAndIsDelete(Long id);
    
    List<Attachment> findByTableAndHostId(@Param("category")Integer category,@Param("hostId")Long hostId);
    
    @Update("update attachment set is_delete=0 where id=#{id}")
	int updateIsDeleteById(Long id);
    
    @Update("update attachment set is_delete=0 where host_id=#{hostId} and category =#{category} and is_delete=1")
   	int updateIsDeleteByHostIdAndCategory(Attachment record);
     
    List<Attachment> selectByhostId(@Param("hostId")String hostId);
   
}