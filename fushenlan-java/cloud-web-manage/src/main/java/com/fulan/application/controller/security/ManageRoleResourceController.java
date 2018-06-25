package com.fulan.application.controller.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.security.domain.RoleResource;
import com.fulan.api.security.service.RoleResourceService;
import com.fulan.application.util.domain.Response;

@Controller
@RequestMapping("manage")
public class ManageRoleResourceController {
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@RequestMapping("/roleResource/save")
	@ResponseBody
	public Response<String> save(String roleId,String resourceIds){
		return roleResourceService.saveBatch(roleId, resourceIds);
	}
	
	@RequestMapping("/roleResource/listByRoleId")
	@ResponseBody
	public Response<List<RoleResource>> listByRoleId(@RequestParam("roleId") Long roleId){
		return roleResourceService.findByRoleIdFM(roleId);
	}
	
	
	
}