package com.fulan.application.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.system.domain.Organization;
import com.fulan.application.util.domain.Response;

public interface ManageOrganizationService extends IService<Organization> {

	//List<OrganizationVo> getListVo();

	/**
     * 分页，条件查询
     *
     * @param parentId
     * @param code
     * @param cnName
     * @param enName
     * @param pageNo
     * @param pageSize
     * @param pageSortFiled
     * @param pageSortType
     * @return
     */
    Page<Organization> listByPage(Organization organization, int pageNo, int pageSize, String pageSortFiled, String pageSortType);

    Response<String> delectOrganizations(String id);

	List<Organization> selectList();
	
	List<Organization> selectListByParentId(String parentId);
	
	Organization  checkCodeByParentId(Organization organization);

	List<Organization> firstList(String id);
	
	String getfirstId();

	List<Organization> listAllChildren(String parentId);

}
