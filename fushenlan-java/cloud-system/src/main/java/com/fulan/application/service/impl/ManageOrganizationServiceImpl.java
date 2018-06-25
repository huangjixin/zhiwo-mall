package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.system.domain.Organization;
import com.fulan.application.mapper.ManageOrganizationMapper;
import com.fulan.application.service.ManageOrganizationService;
import com.fulan.application.util.domain.Response;

@Service
public class ManageOrganizationServiceImpl extends ServiceImpl<ManageOrganizationMapper, Organization> implements ManageOrganizationService {

	@Autowired
	ManageOrganizationMapper manageOrganizationMapper;

	@Override
	public Page<Organization> listByPage(Organization organization, int pageNo, int pageSize, String pageSortFiled,
			String pageSortType) {
		// 组装page，页数、条数、排序字段、排序方式
        Page<Organization> page = new Page<Organization>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc((null == pageSortType || "desc".equals(pageSortType)) ? false : true);
		EntityWrapper<Organization> ew = new EntityWrapper<>(organization);
		
		return this.selectPage(page, ew);
	}

	@Override
	public Response<String> delectOrganizations(String id) {
		//查询所有子孙集合的Id（递归）
		String parentId = id;
		Response<String> res = new Response<String>();
		List <Organization> list =manageOrganizationMapper.firstListByParentId(parentId);
			if(list.size()>0){
				res.setMsg("请先删除子节点");
				res.setData("0");
				return res;
			}
			manageOrganizationMapper.deleteById(parentId);
			res.setMsg("删除成功");
			res.setData("1");
			return res;
	}

	@Override
	public List<Organization> selectList() {
		return manageOrganizationMapper.selectList();
	}

	@Override
	public List<Organization> selectListByParentId(String parentId) {
		return manageOrganizationMapper.firstListByParentId(parentId);
	}

	@Override
	public Organization checkCodeByParentId(Organization organization) {
		return manageOrganizationMapper.checkCodeByParentId(organization);
	}

	@Override
	public List<Organization> firstList(String id) {
		if(null==id){
			String parentId = manageOrganizationMapper.getfirstId();
			return manageOrganizationMapper.firstListByParentId(parentId);
		}
		String parentId = id;
		return manageOrganizationMapper.firstListByParentId(parentId);
	}

	@Override
	public String getfirstId() {
		return manageOrganizationMapper.getfirstId();
	}
	
	/**
	 * 递归将子孙加点加入list
	 */
	@Override
	public List<Organization> listAllChildren(String parentId) {
		List<Organization> organizationList = new ArrayList<Organization>();
		//递归得到子孙节点
		tree(parentId,organizationList);
		return organizationList;
	}
	
	/**
	 * 递归方法
	 */
	public void tree(String parentId,List<Organization> list){	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parent_id", parentId);
		List<Organization> tempList = manageOrganizationMapper.selectByMap(map);
		if(tempList==null||tempList.size()==0){
			return;
		}
		list.addAll(tempList);
		for(Organization organization:tempList){
			tree(organization.getId(),list);
		}
	}

}
