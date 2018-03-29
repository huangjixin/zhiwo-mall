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
import com.zwo.xiyangyang.modules.mem.domain.MemAddress;
import com.zwo.xiyangyang.modules.mem.domain.MemMember;
import com.zwo.xiyangyang.modules.mem.service.IMemAddressService;

@Controller
@RequestMapping("maddress")
public class MemAddressController extends BaseController<MemAddress> {
	@Autowired
	private IMemAddressService addressService;

	@RequestMapping()
	@ResponseBody
	List<MemMember> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<MemAddress> getBaseService() {
		return (IBaseService) addressService;
	}
}
