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
import com.zwo.xiyangyang.modules.pr.domain.PrProduct;
import com.zwo.xiyangyang.modules.pr.service.IProductService;

@Controller
@RequestMapping("pproduct")
public class ProductController extends BaseController<PrProduct> {
	@Autowired
	private IProductService productService;

	@RequestMapping()
	@ResponseBody
	List<PrProduct> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<PrProduct> getBaseService() {
		return (IBaseService) productService;
	}
}
