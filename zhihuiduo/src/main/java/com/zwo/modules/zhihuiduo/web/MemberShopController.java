package com.zwo.modules.zhihuiduo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.zhihuiduo.dto.ProductExtention;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

/**
 * 会员登录控制器。
 * 
 * @author 黄记新 2017.8.8
 *
 */
@Controller
@Lazy(true)
public class MemberShopController extends BaseController {
	@Autowired
	@Lazy(true)
	private IPrImageService imageService;

	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;

	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;

	@Autowired
	@Lazy(true)
	private IPrProductPackagePriceService packagePriceService;
	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService propertyValueService;
	@Autowired
	@Lazy(true)
	private IShopService shopService;
	@Autowired
	@Lazy(true)
	private IShopCategoryService shopCategoryService;

	 @SuppressWarnings("rawtypes")
	 private RedisTemplate redisTemplate;

	private static final String basePath = "views/member/";

	public MemberShopController() {
		super();
		if(SpringContextHolder.getApplicationContext().containsBean("redisTemplate")){
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}


	/**
	 * 商铺具体信息。
	 * 
	 * @param goodsId
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "memberShop/{shopId:[0-9]+}", method = RequestMethod.GET)
	public String memberShop(@PathVariable String shopId, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String jsonString=null;
		ProductExtention productExtention = null;
		productExtention = new ProductExtention();
		
		Shop shop = shopService.selectByPrimKey(shopId);
		
		List<PrProduct> products = prductService
				.selectPrProductsByShopId(shopId);
		productExtention.setShop(shop);
		productExtention.setGoodsList(products);
		/*int productsCount = prductService.selectPrProductsCountByShopId(shopId);

		uiModel.addAttribute("shop", shop);
		uiModel.addAttribute("products", products);
		uiModel.addAttribute("productsCount", productsCount);*/
		
		jsonString = JSONObject.toJSONString(productExtention,true);
		uiModel.addAttribute("rawData", jsonString);
		
		return basePath + "memberShop";
	}

}
