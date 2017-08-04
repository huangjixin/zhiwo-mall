package com.zwo.modules.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.shop.domain.ShopCategory;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("shopCategory")
@Lazy(true)
public class ShopCategoryController extends BaseController<ShopCategory> {
	@Autowired
	@Lazy(true)
	private IShopCategoryService shopCategoryService;

	/*
	 * @Autowired
	 * 
	 * @Lazy(true)
	 */
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/system/shopCategory/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "shopCategory_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(@Valid ShopCategory shopCategory, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("shopCategory", shopCategory);
		return basePath + "shopCategory_edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ShopCategory shopCategory = null;
		/*
		 * ValueOperations<String, Object> valueOperations = null;
		 * if(redisTemplate!=null){ valueOperations
		 * =redisTemplate.opsForValue(); shopCategory = (ShopCategory)
		 * valueOperations.get(id); }
		 */

		if (shopCategory == null) {
			shopCategory = shopCategoryService.selectByPrimaryKey(id);
			/*
			 * if(valueOperations != null ){ valueOperations.set(id, shopCategory);
			 * }
			 */
		}

		uiModel.addAttribute("shopCategory", shopCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath + "shopCategory_edit";
	}

	@RequestMapping(value = { "test" }, method = RequestMethod.GET)
	public String test(Model uiModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
