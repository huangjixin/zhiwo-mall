package com.zwo.modules.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import com.zwo.modules.system.domain.TbUserUserGroup;

import tk.mybatis.mapper.common.Mapper;

public interface TbUserMapper extends Mapper<TbUser> {
	/**
	 * 根据查询条件进行查询以及组名进行查询
	 * @param example
	 * @param groupName
	 * @return
	 */
	/*List<TbUserUserGroup> selectByExampleAndGroupCode(@Param(value="example") TbUserCriteria example,
			@Param(value="groupName") String groupName);*/
}