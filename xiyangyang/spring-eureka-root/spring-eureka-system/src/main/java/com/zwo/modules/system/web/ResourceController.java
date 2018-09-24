/**
 * 
 */
package com.zwo.modules.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.modules.core.service.IBaseService;
import com.zwo.modules.core.web.BaseController;
import com.zwo.modules.system.domain.Resource;
import com.zwo.modules.system.service.IResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 黄记新
 *
 */
@Api(tags = "Resource控制器Api", description = "资源控制器api，包括提供基础的增删改查")
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController<Resource> {
	@Autowired
	private IResourceService resourceService;

	@Override
	protected IBaseService<Resource> getBaseService() {
		return resourceService;
	}

	@ApiOperation(value = "获取资源名信息", notes = "")
	@GetMapping("getResourcename")
	@ResponseBody
	public String getResourceName() {
		return "hi,Tony";
	}

	@ApiOperation(value = "删除资源", notes = "")
	@DeleteMapping("deteleResource")
	@ResponseBody
	public String deteleResource() {
		return "你已经删除了资源信息";
	}
}
