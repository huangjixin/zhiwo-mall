package com.zwo.modules.zhihuiduo.web;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.zwotech.common.web.BaseController;

/**
 * 
 * @author 黄记新 2017.8.8
 *
 */
@Controller
@Lazy(true)
public class GoodsController extends BaseController {
	/*@Autowired
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

//	@SuppressWarnings("rawtypes")
//	private RedisTemplate redisTemplate = SpringContextHolder
//			.getBean("redisTemplate");

	private static final String basePath = "views/goods/";

	*//**
	 * 商品具体信息。
	 * 
	 * @param goodsId
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 *//*
	@RequestMapping(value = { "goodsDetail" }, method = RequestMethod.GET)
	public String goodsDetail(@RequestParam String goodsId, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String rootDir = httpServletRequest.getSession().getServletContext()
				.getRealPath("/");
		String goodsDetailJspUri = rootDir + "WEB-INF" + File.separator
				+ "views" + File.separator + "goods" + goodsId + ".jsp";
		Path path = Paths.get(goodsDetailJspUri);

		if (Files.exists(path)) {
			return basePath + goodsId;
		}

		PrProductWithBLOBs product = prductService.selectByPrimKey(goodsId);
		if(!"".equals(product.getContent())){
			String str="<img style=\"**\" alt=\"**\" src=\"**\">";
			String cont = product.getContent();
			String regex="(<img.*)src=";
			cont.replaceAll(regex,"src=\"/images/heart.png\" $1 data-original=");
			product.setContent(cont);
		}
		
		if (product != null) {
			if(product.getShopId()!=null){
				int shopProductsCount = prductService.selectPrProductsCountByShopId(product.getShopId());
				uiModel.addAttribute("shopProductsCount", shopProductsCount);
			}
			
			if (null != product.getShopId()) {
				// 查询店铺信息。
				Shop shop = shopService.selectByPrimKey(product.getShopId());
				uiModel.addAttribute("shop", shop);
				if (shop != null) {
					// 查询店铺所有的商品，统一用goods。
					List<PrProduct> goodsList = prductService.selectPrProductsByShopId(shop.getId());
					uiModel.addAttribute("goodsList", goodsList);
				}

			}

			List<PrProductPackagePrice> packagePrices = null;
			List<PrProductPropertyValue> productPropertyValues = null;
			packagePrices = packagePriceService.selectByProductId(product
					.getId());
			productPropertyValues = this.propertyValueService
					.selectByProductId(product.getId());

			// 商品属性值。
			uiModel.addAttribute("propertyValuesString",
					JSONArray.toJSONString(productPropertyValues));
			uiModel.addAttribute("propertyValues", productPropertyValues);
			uiModel.addAttribute("packagePrices", packagePrices);

			// 商品轮播图。
			List<PrImage> prImages = imageService.selectByProductId(
					product.getId(), PrImageType.SWIPER);
			uiModel.addAttribute("swiperImages", prImages);
		}
		// 商品属性。
		List<PrProductProperty> properties = productPropertyService.listAll();
		uiModel.addAttribute("properties", properties);
		uiModel.addAttribute("propertiesString",
				JSONArray.toJSONString(properties));
		uiModel.addAttribute("product", product);
		return basePath + "goodsDetail";
	}*/

}
