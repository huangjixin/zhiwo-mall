package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.domain.Version;

public interface VersionMapper extends BaseMapper<Version>{

	@Select("SELECT version_code from version where type=#{type} order by create_time desc limit 1")
	String findVersionByType(int type);

}
