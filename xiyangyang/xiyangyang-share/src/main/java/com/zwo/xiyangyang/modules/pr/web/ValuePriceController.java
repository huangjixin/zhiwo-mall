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
import com.zwo.xiyangyang.modules.pr.domain.PrValuePrice;
import com.zwo.xiyangyang.modules.pr.service.IValuePriceService;

@Controller
@RequestMapping("pvalue-price")
public class ValuePriceController extends BaseController<PrValuePrice> {
	@Autowired
	private IValuePriceService valuePriceService;

	@RequestMapping()
	@ResponseBody
	List<PrValuePrice> defaultMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<PrValuePrice> getBaseService() {
		return (IBaseService) valuePriceService;
	}
}
