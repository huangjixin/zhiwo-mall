package com.zwo.modules.system.dao;

import java.util.List;

import com.zwo.modules.system.domain.TbResources;

import tk.mybatis.mapper.common.Mapper;

public interface TbResourcesMapper extends Mapper<TbResources> {
	
    /**
     * 工具用户名或者电话号码进行查询
     * @param usernameOrMPhone
     * @return
     */
    List<TbResources> selectByUsernameOrMPhone(String usernameOrMPhone);
    
    /**
     * 根据用户组名进行查询。
     * @param Groupname
     * @return
     */
    List<TbResources> selectByGroupname(String groupname);
    
    /**
     * 根据角色名进行查询。
     * @param Groupname
     * @return
     */
    List<TbResources> selectByRolename(String rolename);
    
}