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
import com.zwo.xiyangyang.modules.sys.domain.User;
import com.zwo.xiyangyang.modules.sys.domain.UserGroup;
import com.zwo.xiyangyang.modules.sys.service.IUserGroupService;

@Controller
@RequestMapping("user-group")
public class UserGroupController extends BaseController<UserGroup> {
	@Autowired
	private IUserGroupService userGroupService;

	@RequestMapping()
	@ResponseBody
	List<User> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<UserGroup> getBaseService() {
		return (IBaseService) userGroupService;
	}
}
