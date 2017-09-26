package com.zwo.modules.zhihuiduo.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageType;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.service.IGroupPurcseService;
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
public class MemberGoodsController extends BaseController<PrProduct> {
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
	@Autowired
	@Lazy(true)
	private IGroupPurcseService groupPurcseService;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	private static final String basePath = "views/goods/";

	private static final String KEY_GOODSDETAIL_INFO = "_key_GoodsDetail_Info";

	public MemberGoodsController() {
		super();
		if (SpringContextHolder.getApplicationContext().containsBean(
				"redisTemplate")) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}

	/**
	 * 商品具体信息。
	 * 
	 * @param goodsId
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "goodsDetail/{goodsId}", method = RequestMethod.GET)
	public String goodsDetail(@PathVariable String goodsId, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String rootDir = httpServletRequest.getSession().getServletContext()
				.getRealPath("/");
		String goodsDetailJspUri = rootDir + "WEB-INF" + File.separator
				+ "views" + File.separator + "goods" + goodsId + ".jsp";
		/*
		 * Path path = Paths.get(goodsDetailJspUri);
		 * 
		 * if (Files.exists(path)) { return basePath + goodsId; }
		 */

		String jsonString = null;
		ProductExtention productExtention = null;
		List<GroupPurcseMember> groupPurcseMembers = null;
		GroupPurcse groupPurcse = null;
		PrProductWithBLOBs product = null;
		Shop shop = null;
		List<PrProductPackagePrice> packagePrices = null;
		List<PrProductPropertyValue> productPropertyValues = null;
		List<GroupPurcse> groupPurcses = null;
		// 商品属性。
		List<PrProductProperty> properties;
		List<PrProduct> goodsList = null;
		List<PrImage> swiperImages = null;

		String key = goodsId + KEY_GOODSDETAIL_INFO;

		if (redisTemplate != null) {
			productExtention = (ProductExtention) redisTemplate.opsForValue()
					.get(key);

			if (productExtention == null) {
				productExtention = new ProductExtention();

				product = prductService.selectByPrimKey(goodsId);
				if (product != null) {
					try {
						BeanUtils.copyProperties(productExtention, product);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}

					groupPurcses = groupPurcseService.selectGroupPurcseByPId(
							goodsId, false);
					properties = productPropertyService.listAll();
					packagePrices = packagePriceService
							.selectByProductId(product.getId());
					productPropertyValues = this.propertyValueService
							.selectByProductId(product.getId());

					if (product.getShopId() != null) {
						shop = shopService.selectByPrimKey(product.getShopId());
						goodsList = prductService.selectPrProductsByShopId(shop
								.getId());
						productExtention.setShopIcon(shop.getIcon());
						productExtention.setShopName(shop.getName());
					}
					// 商品轮播图。
					swiperImages = imageService.selectByProductId(
							product.getId(), PrImageType.SWIPER);

					productExtention.setGoodsList(goodsList);
					productExtention.setSwpierImages(swiperImages);
					productExtention.setPackagePrices(packagePrices);
					productExtention
							.setProductPropertyValues(productPropertyValues);
					productExtention.setGroupPurcseMembers(groupPurcseMembers);
					productExtention.setGroupPurcses(groupPurcses);
					productExtention.setProperties(properties);

					redisTemplate.opsForValue().set(key, productExtention);
					redisTemplate.expire(key, 30, TimeUnit.SECONDS);
				}

			}
		} else {
			productExtention = new ProductExtention();

			product = prductService.selectByPrimKey(goodsId);
			if (product != null) {
				try {
					BeanUtils.copyProperties(productExtention, product);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				groupPurcses = groupPurcseService.selectGroupPurcseByPId(
						goodsId, false);
				properties = productPropertyService.listAll();
				packagePrices = packagePriceService.selectByProductId(product
						.getId());
				productPropertyValues = this.propertyValueService
						.selectByProductId(product.getId());

				if (product.getShopId() != null) {
					shop = shopService.selectByPrimKey(product.getShopId());
					goodsList = prductService.selectPrProductsByShopId(shop
							.getId());
					productExtention.setShopIcon(shop.getIcon());
					productExtention.setShopName(shop.getName());
				}
				// 商品轮播图。
				swiperImages = imageService.selectByProductId(product.getId(),
						PrImageType.SWIPER);

				productExtention.setGoodsList(goodsList);
				productExtention.setSwpierImages(swiperImages);
				productExtention.setPackagePrices(packagePrices);
				productExtention
						.setProductPropertyValues(productPropertyValues);
				productExtention.setGroupPurcseMembers(groupPurcseMembers);
				productExtention.setGroupPurcses(groupPurcses);
				productExtention.setProperties(properties);
			}

		}
		// String content = product.getContent();
		// content = HtmlUtils.htmlEscape(content);
		// productExtention.setContent(content);

		jsonString = JSONObject.toJSONString(productExtention, true);
		uiModel.addAttribute("rawData", jsonString);

		return basePath + "goodsDetail";
	}

	@RequestMapping(value = "goodsByCategory", method = RequestMethod.GET)
	@ResponseBody
	public DatagridPage<PrProduct> goodsByCategory(@RequestParam String prCId,
			@ModelAttribute PageInfo<PrProduct> pageInfo, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		pageInfo = prductService.selectByCategoryIdPageInfo(prCId, pageInfo);
		return super.setPage(pageInfo);
	}
}
