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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.domain.ShopWithBLOBs;
import com.zwo.modules.shop.service.IShopService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("shop")
@Lazy(true)
public class ShopController extends BaseController<Shop> {
	@Autowired
	@Lazy(true)
	private IShopService shopService;

	/*
	 * @Autowired
	 * 
	 * @Lazy(true)
	 */
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/shop/shop/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "shop_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid Shop shop, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("shop", shop);
		return basePath + "shop_edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Shop shop = null;
		/*
		 * ValueOperations<String, Object> valueOperations = null;
		 * if(redisTemplate!=null){ valueOperations
		 * =redisTemplate.opsForValue(); shop = (Shop)
		 * valueOperations.get(id); }
		 */

		if (shop == null) {
			shop = shopService.selectByPrimaryKey(id);
			/*
			 * if(valueOperations != null ){ valueOperations.set(id, shop);
			 * }
			 */
		}

		uiModel.addAttribute("shop", shop);
		uiModel.addAttribute("operation", "edit");
		return basePath + "shop_edit";
	}
	

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid ShopWithBLOBs tbshop, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = shopService.insertSelective(tbshop);
		if(res==1){
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/shop/create";
	}
	
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid ShopWithBLOBs shop, BindingResult result, Model uiModel,
			RedirectAttributes attr,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.shopService.updateByPrimaryKeySelective(shop);
		return res;
	}

}
