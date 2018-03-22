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
import com.zwo.xiyangyang.modules.sys.domain.Org;
import com.zwo.xiyangyang.modules.sys.service.IOrgService;

@Controller
@RequestMapping("org")
public class OrgController extends BaseController<Org> {
	@Autowired
	private IOrgService orgService;

	@RequestMapping()
	@ResponseBody
	List<Org> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return orgService.selectByExample(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<Org> getBaseService() {
		return (IBaseService) orgService;
	}
}
