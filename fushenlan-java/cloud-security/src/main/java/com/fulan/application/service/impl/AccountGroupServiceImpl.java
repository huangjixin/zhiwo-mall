package com.fulan.application.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import com.fulan.api.security.domain.AccountGroup;
import com.fulan.api.security.domain.TagTarget;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.vo.AccountGroupVo;
import com.fulan.application.mapper.AccountGroupMapper;
import com.fulan.application.mapper.GroupMapper;
import com.fulan.application.mapper.GroupTagMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.AccountGroupService;
import com.fulan.application.service.GroupService;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 用户组 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
@Service
@TxTransaction
@Transactional
public class AccountGroupServiceImpl extends ServiceImpl<AccountGroupMapper, AccountGroup> implements AccountGroupService {
    
    @Autowired
    private AccountGroupMapper accountGroupMapper;

    @Override
    public List<AccountGroupVo> listAccountGroupByGroupId(Long groupId) {
        
        return accountGroupMapper.listAccountGroupByGroupId(groupId);
    }
    

}
