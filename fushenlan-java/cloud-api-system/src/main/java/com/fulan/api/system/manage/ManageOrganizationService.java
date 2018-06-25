package com.fulan.api.system.manage;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.system.domain.Organization;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "system")
public interface ManageOrganizationService {

	/*@PostMapping(value = "/manage/organization/list")
	PageInfo<Organization> listByPage(
			@RequestBody Organization organization,
            @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType
			);*/

	@PostMapping(value = "/manage/organization/findById")
	Organization getOrganizationById(String id);

	@PostMapping(value = "/manage/organization/insert")
	Response<String> insertOrganization(Organization organization);

	@PostMapping(value = "/manage/organization/update")
	Response<String> updateOrganization(Organization organization);

	@PostMapping(value = "/manage/organization/delete")
	Response<String> deleteOrganization(String id);

	@PostMapping(value = "/manage/organization/list")
	List<Organization> getOrganizationList();

	@PostMapping(value = "/manage/organization/firstList")
	List<Organization> getFirstOrganizationList(String id);
	
	@PostMapping(value = "/manage/organization/firstListWithoutId")
	List<Organization> getFirstOrganizationList();

	@PostMapping(value = "/manage/organization/findByParentId")
	List<Organization> getOrganizationListByParentId(String parentId);
	
	@PostMapping(value = "/manage/organization/listAllChildren")
	List<Organization> listAllChildren(@RequestBody  String parentId); 
}
