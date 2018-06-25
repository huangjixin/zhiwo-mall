package com.fulan.application.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.security.domain.AccountGroup;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.vo.AccountGroupVo;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
public interface GroupService extends IService<UserGroup> {
   
    /**
     *条件查询，(带分页) 
     * @return
     */
    public PageInfo<UserGroup> listByParam(UserGroup group,int pageNo, int pageSize,
            String pageSortFiled,String pageSortType);
    
    
   /**
    * 新增及修改方法
    * @param group 用户组对象
    * @param tagStr 关联标签字符串,（逗号隔开:123,456）
    * @param userStr 关联用户字符串,（逗号隔开:123,456）
    * @param newTag 新建的标签（逗号隔开:123,456）
    * @return
    */
    public int saveGroup(UserGroup group, String tagStr, String userStr, String newTag); 
    
    
    /**
     * 删除用户组
     * @param groupIds
     * @return
     */
    public String deleteGroups (String groupIds);

    
    /**
     *查询该组下用户数量
     * @param groupId
     * @return
     */
    public int countUserNum(Long groupId);

    
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public UserGroup selectGroupById(Long id);

    
    /**
     * 根据用户查询用户组
     * @param accountId
     * @return
     */
    public UserGroup selectGroupByAccountId(Long accountId);
    
    
}
