package com.fulan.application.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.system.domain.Organization;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.ManageOrganizationService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = "OrganizationApi", description = "资源接口")
@RestController
@RequestMapping(value ="/manage/organization")
public class ManageOrganizationController {

	private static final Logger LOG = LoggerFactory.getLogger(ManageOrganizationController.class);
	
	@Autowired
	ManageOrganizationService manageOrganizationService;
	
	@Autowired
	IdGenerator idGenerator;
	
	/**
     * 分页查询组织架构
     *
     * @param organization  组织架构实体
     * @param pageNo        要跳转的页数
     * @param pageSize      每页条数
     * @return
     */
    @RequestMapping(value = "/list", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> listByPage(/*
    		@RequestBody Organization organization,
            @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType
    		*/) {
        try {
        	return manageOrganizationService.selectList();
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 根据id查找单个组织架构
     *
     * @param id 组织架构Id
     * @return
     */
    @RequestMapping(value = "/findById")
    public Organization findById(@RequestBody String id) {
        try {
        	Organization organization =manageOrganizationService.selectById(id);;
            return organization;
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }

    }
    
    /**
     * 新增组织架构
     *
     * @param organization
     * @return
     */
    @PostMapping("/insert")
    public Response<String> insert(
            @ApiParam(
                    name = "organization",
                    value = "不能为空字段：</br>code</br>cnName</br>value</br>systemFlag</br>" +
                            "选传参数：</br>parentId:当为顶层节点时不传或传0</br>sort:同层级排序"
            )
            @RequestBody Organization organization) {
    	organization.setId(String.valueOf(idGenerator.generate()));
        try {
        	Response<String> res =new Response<String>();
        	Organization organ = manageOrganizationService.checkCodeByParentId(organization);
        	if(null==organ){
        		boolean boo = manageOrganizationService.insert(organization);
        		if(boo){
        			res.setCode("true");
        		}
        		res.setMsg("");
                return res;
        	}
        	res.setCode("false");
        	res.setMsg("该节点下已存在该组织");
        	return res;
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    /**
     * 更新组织架构
     *
     * @param organization
     * @return
     */
    @PostMapping(value = "/update")
    public Response<String> update(@ApiParam("id不可不传") @RequestBody Organization organization) {
        try {
            Response<String> res =new Response<String>();
        	Organization organ = manageOrganizationService.checkCodeByParentId(organization);
        	if(null==organ){
        		boolean boo = manageOrganizationService.updateById(organization);
        		if(boo){
        			res.setCode("true");
        		}
        		res.setMsg("");
                return res;
        	}
        	res.setCode("false");
        	res.setMsg("该节点下已存在该组织");
        	return res;
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
   
    /**
     * 删除组织架构
     *
     * @param organization
     * @return
     */
    @PostMapping(value = "/delete")
    public Response<String> delete(@ApiParam("id不可不传") @RequestBody String id) {
        try {
            return manageOrganizationService.delectOrganizations(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    @RequestMapping(value = "/firstList", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> firstList(@RequestBody String id) {
        try {
        	return manageOrganizationService.firstList(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    @RequestMapping(value = "/firstListWithoutId", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> firstList() {
        try {
        	return manageOrganizationService.firstList(null);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    @RequestMapping(value = "/findByParentId", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> findByParentId(@RequestBody String parentId) {
        try {
        	return manageOrganizationService.selectListByParentId(parentId);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 递归获取所有子孙节点
     */
    @RequestMapping(value = "/listAllChildren", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> listAllChildren(@RequestBody String parentId) {
        try {
        	return manageOrganizationService.listAllChildren(parentId);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
}
