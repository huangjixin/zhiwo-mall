package com.zwo.xiyangyang.modules.guess.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessCategory;
import com.zwo.xiyangyang.modules.guess.service.IGuessCategoryService;

@Controller
@Lazy(value=true)
@RequestMapping("gcategory")
public class GuessCategoryController extends BaseController<GuessCategory> {

	@Autowired
	private IGuessCategoryService categoryService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessCategory> getBaseService() {
		return (IBaseService)categoryService;
	}
	
	@RequestMapping("getCategoryTree")
	@ResponseBody
	List<GuessCategory> getCategoryTree(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<GuessCategory> categories = categoryService.getCategoryTree(null);
		return categories;
	}

}
