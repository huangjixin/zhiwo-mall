package com.zwo.xiyangyang.modules.sys.web;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.sys.domain.User;
import com.zwo.xiyangyang.modules.sys.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<User> {
	@Resource(name="userServiceImpl")
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	List<User> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<User> getBaseService() {
		return (IBaseService) userService;
	}
	
	@RequiresUser
	@RequestMapping(value="getRes",method = RequestMethod.GET)
	@ResponseBody
	Set<String> getRes(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return userService.findPermissions("1");
	}
	
	@Override
	@RequiresUser
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	protected User getById(@PathVariable(name = "id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		User record = getBaseService().selectByPrimaryKey(id);
		return record;
	}
}
