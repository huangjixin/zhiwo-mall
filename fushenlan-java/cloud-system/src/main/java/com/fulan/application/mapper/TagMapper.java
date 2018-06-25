package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;

public interface TagMapper extends BaseMapper<Tag> {

	@Select("select t.id,t.tag_name as tagName,t.parent_id as parentId,t.catagory,t.moudle,t.create_user as createUser"
			+ ",t.create_time as createTime,t.modify_user as modifyUser"
			+ ",t.modify_time as modifyTime from tag t where t.catagory=#{catagory}")
	List<Tag> findByCatagory(Integer catagory);


	List<Tag>  findBymoudle();
	
	
	List<Tag>  findByparentId(@Param("parentId")Long parentId ,@Param("catagory")Integer catagory);
	
	List<SingleTagVo> findByTagName(@Param("page")Page<SingleTagVo> page,@Param("tagName")String tagName,@Param("parentTagName")String parentTagName,@Param("catagory")Integer catagory);
	
	int countByTagName(@Param("tagName")String tagName,@Param("parentTagName")String parentTagName,@Param("catagory")Integer catagory);
	
	List<TagVo> findByParentTagName(@Param("page")Page<TagVo> page,@Param("parentTagName")String parentTagName,@Param("catagory")Integer catagory);
	
	int countByParentTagName(@Param("parentTagName")String parentTagName,@Param("catagory")Integer catagory);
	
}
