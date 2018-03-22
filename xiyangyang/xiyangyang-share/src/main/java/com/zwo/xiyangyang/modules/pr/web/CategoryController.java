package com.zwo.xiyangyang.modules.pr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.pr.domain.PrCategory;
import com.zwo.xiyangyang.modules.pr.service.ICategoryService;

@Controller
@RequestMapping("pcategory")
public class CategoryController extends BaseController<PrCategory> {
	@Autowired
	private ICategoryService pcategoryService;

	@RequestMapping()
	@ResponseBody
	List<PrCategory> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<PrCategory> getBaseService() {
		return (IBaseService) pcategoryService;
	}
}
