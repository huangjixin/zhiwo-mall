package com.zwo.xiyangyang.modules.mem.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.mem.domain.MemMember;
import com.zwo.xiyangyang.modules.mem.service.IMememberService;

@Controller
@RequestMapping("memember")
public class MememberController extends BaseController<MemMember> {
	@Autowired
	private IMememberService mememberService;

	@RequestMapping()
	@ResponseBody
	List<MemMember> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<MemMember> getBaseService() {
		return (IBaseService) mememberService;
	}
}
