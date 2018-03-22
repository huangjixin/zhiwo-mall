package com.zwo.xiyangyang.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.sys.domain.Role;
import com.zwo.xiyangyang.modules.sys.service.IRoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController<Role> {
	@Autowired
	private IRoleService roleService;

	@RequestMapping()
	@ResponseBody
	List<Role> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return roleService.selectByExample(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<Role> getBaseService() {
		return (IBaseService) roleService;
	}
}
