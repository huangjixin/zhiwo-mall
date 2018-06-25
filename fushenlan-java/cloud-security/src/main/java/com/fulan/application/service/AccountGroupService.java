package com.fulan.application.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.security.domain.AccountGroup;
import com.fulan.api.security.vo.AccountGroupVo;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
public interface AccountGroupService extends IService<AccountGroup> {

    /**
     * 根据groupId查询
     * @param groupId
     * @return
     */
    public List<AccountGroupVo> listAccountGroupByGroupId (Long groupId);
   
    
}
