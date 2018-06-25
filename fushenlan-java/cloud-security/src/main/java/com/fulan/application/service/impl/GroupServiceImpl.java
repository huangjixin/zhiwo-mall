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
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.TagService;
import com.fulan.application.mapper.AccountGroupMapper;
import com.fulan.application.mapper.GroupMapper;
import com.fulan.application.mapper.GroupTagMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.GroupService;
import com.fulan.application.service.GroupTagService;
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
public class GroupServiceImpl extends ServiceImpl<GroupMapper, UserGroup> implements GroupService {
    
    @Autowired
    private IdGenerator idGenerator;
    
    @Autowired
    private GroupMapper groupMapper;
    
    @Autowired
    private GroupTagMapper groupTagMapper;
    
    @Autowired
    private AccountGroupMapper accountGroupMapper;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private GroupTagService groupTagService;
    
    @Override
    public PageInfo<UserGroup> listByParam(UserGroup group, int pageNo,
            int pageSize,String pageSortFiled,
            String pageSortType) {
        
        Page<UserGroup> page = new Page<UserGroup>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        //倒叙
        page.setAsc("DESC".equals(pageSortType) ? false : true);
        
        //EntityWrapper<UserGroup> ew = new EntityWrapper<UserGroup>(group);
        EntityWrapper  ew = new EntityWrapper();
        ew.setEntity(new UserGroup());
        //模糊查询
        if (StringUtils.isNotEmpty(group.getGroupName())) {
            ew.like("group_name", group.getGroupName(),SqlLike.DEFAULT);
        }
        if (StringUtils.isNotEmpty(group.getGroupDesc())) {
            ew.like("group_desc", group.getGroupDesc(),SqlLike.DEFAULT);
        }
        page = this.selectPage(page, ew);
        
        PageInfo<UserGroup> pageInfo = new PageInfo<UserGroup>();
        pageInfo.setRecords(page.getRecords());
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(page.getPages());
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }


    @Override
    public int saveGroup(UserGroup group, String tagStr, String userStr, String newTag) {
        
        try {
            Long groupId = group.getId();
            if (groupId != null) {// 跟新
                int i = groupMapper.updateById(group); 
                updateTagAndUser(tagStr, userStr, groupId, newTag);
                return i;
            } else {   // 新增
                groupId = idGenerator.generate();
                group.setId(groupId);
                int i = groupMapper.insert(group);
                updateTagAndUser(tagStr, userStr, groupId, newTag);
                return i; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public String deleteGroups(String groupIds) {
        String returnStr = "";
        try {
            if (StringUtils.isNotEmpty(groupIds)) {
                String[] Arr = groupIds.split(",");
                for (int j = 0; j < Arr.length; j++) {
                    //先判断用户组下面时候挂着用户信息，有就不能删
                    List<AccountGroupVo> agList = accountGroupMapper.listAccountGroupByGroupId(Long.parseLong(Arr[j]));
                    if (agList != null && agList.size() > 0) {
                        returnStr +=  agList.get(0).getGroupName()+"   ";
                    } else {
                        groupMapper.deleteById(Long.parseLong(Arr[j]));
                        Map<String,Object> map = new HashMap<String, Object>();
                        groupTagService.deleteByHostId(Long.parseLong(Arr[j]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return returnStr;
    }
    
    // 关联标签和用户中间表
    public void updateTagAndUser(String tagStr, String userStr,Long groupId, String newTag) {
       
        
        Map <String,Object> map = new HashMap<String, Object>();
        map.put("host_id", groupId);
        map.put("type", 1);
        //先删除之前关联的,重新绑定关联
        groupTagMapper.deleteByMap(map);
        //关联标签
        if (StringUtils.isNotEmpty(tagStr)) {
            String[] tagArr = tagStr.split(",");
            for (int j = 0; j < tagArr.length; j++) {
                TagTarget tt = new TagTarget();
                tt.setId(idGenerator.generate());
                tt.setHostId(groupId);
                tt.setTagId(Long.parseLong(tagArr[j]));
                tt.setCreateTime(new Date());
                //1--用户组；2--公开课
                tt.setType(1);
                int x = groupTagMapper.insert(tt);
            }
        } 
        
        Map <String,Object> map2 = new HashMap<String, Object>();
        map2.put("group_id", groupId);
        accountGroupMapper.deleteByMap(map2);
        //关联用户
        if (StringUtils.isNotEmpty(userStr)) {
            String[] userAddr = userStr.split(",");
            for (int j = 0; j < userAddr.length; j++) {
                AccountGroup ag = new AccountGroup();
                ag.setId(idGenerator.generate());
                ag.setAccountId(Long.parseLong(userAddr[j]));
                ag.setGroupId(groupId);
                accountGroupMapper.insert(ag);
            }
        }
        
        //新增标签入库并关联用户组
        if (StringUtils.isNotEmpty(newTag)) {
            String[] tagAddr = newTag.split(",");
            for (int j = 0; j < tagAddr.length; j++) { 
                
                //新增标签
                Tag tag = new Tag();
                Long id = idGenerator.generate();
                tag.setId(id);
                tag.setTagName(tagAddr[j]);
                tag.setCatagory(1);  //1--用户组
                tag.setMoudle(1);    //1--学习系统
                tag.setCreateTime(new Date());
                tagService.insertDetail(tag);
                
                //关联中间库
                TagTarget tt = new TagTarget();
                tt.setId(idGenerator.generate());
                tt.setHostId(groupId);
                tt.setTagId(id);
                tt.setCreateTime(new Date());
                //1--用户组；2--公开课
                tt.setType(1);
                groupTagMapper.insert(tt);
            }
        }
    }

    @Override
    public int countUserNum(Long groupId) {
        return accountGroupMapper.countUser(groupId);
    }

    @Override
    public UserGroup selectGroupById(Long id) {
        
        return groupMapper.selectById(id);
    }


    @Override
    public UserGroup selectGroupByAccountId(Long accountId) {
        List<UserGroup> list = groupMapper.selectGroupByAccountId(accountId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
