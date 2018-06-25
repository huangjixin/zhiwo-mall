package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.TagTarget;

public interface TagTargetMapper extends BaseMapper<TagTarget> {

	@Select("select t.id,t.type,t.host_id as hostId,t.tag_id as tagId from el_tag_target t where t.type=#{type}")
	List<TagTarget> findAllPublicClassTag(Integer type);

}
