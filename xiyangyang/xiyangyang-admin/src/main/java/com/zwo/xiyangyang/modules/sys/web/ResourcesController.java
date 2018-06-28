package com.zwo.xiyangyang.modules.sys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.sys.domain.Resources;
import com.zwo.xiyangyang.modules.sys.service.IResourcesService;

@Controller
@RequestMapping("resources")
public class ResourcesController extends BaseController<Resources> {
	@Resource(name="resourcesServiceImpl")
	private IResourcesService resourcesService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	List<Resources> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return resourcesService.selectByExample(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<Resources> getBaseService() {
		return (IBaseService) resourcesService;
	}
}
