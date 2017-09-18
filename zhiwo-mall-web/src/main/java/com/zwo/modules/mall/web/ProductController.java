package com.zwo.modules.mall.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageCriteria;
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
import com.zwo.modules.shop.domain.ShopWithBLOBs;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("product")
@Lazy(true)
public class ProductController extends BaseController<PrProduct> {

	@Autowired
	@Lazy(true)
	private IPrImageService imageService;

	@Autowired
	@Lazy(true)
	private IShopService shopService;
	@Autowired
	@Lazy(true)
	private IPrductService productService;
	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService productPropertyValueService;
	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;
	@Autowired
	@Lazy(true)
	private IPrProductPackagePriceService packagePriceService;

	private static final String basePath = "views/mall/product/";

	private RedisTemplate redisTemplate;

	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "product_list";
	}

	@RequiresPermissions("mall:product:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid PrProductWithBLOBs product,
			@RequestParam(required = false) String propertyValues,
			@RequestParam(required = false) String propertyPrices,
			BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String id = UUID.randomUUID().toString().replaceAll("-", "");
		product.setId(id);
		uiModel.addAttribute("product", product);
		// 商品属性。
		List<PrProductProperty> properties = productPropertyService.listAll();
		uiModel.addAttribute("properties", properties);
		uiModel.addAttribute("propertiesString",
				JSONArray.toJSONString(properties));

		return basePath + "product_edit";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,
			@RequestParam(required = false) String propertyValues,
			@RequestParam(required = false) String propertyPrices,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}

		PrProductWithBLOBs product = productService.selectByPrimKey(id);

		// 商品属性。
		List<PrProductProperty> properties = productPropertyService.listAll();
		uiModel.addAttribute("properties", properties);
		uiModel.addAttribute("propertiesString",
				JSONArray.toJSONString(properties));

		List<PrProductPackagePrice> packagePrices = null;
		List<PrProductPropertyValue> productPropertyValues = null;

		packagePrices = packagePriceService.selectByProductId(product.getId());
		productPropertyValues = this.productPropertyValueService
				.selectByProductId(product.getId());
		uiModel.addAttribute("packagePrices", packagePrices);
		uiModel.addAttribute("packagePricesString",
				JSONArray.toJSONString(packagePrices));

		uiModel.addAttribute("propertyValues", productPropertyValues);
		uiModel.addAttribute("propertyValuesString",
				JSONArray.toJSONString(productPropertyValues));

		// 商品属性图。
		// List<PrImage> list = productService.selectByProductId(id,false);
		List<PrImage> list = imageService.selectByProductId(id,
				PrImageType.PROP);
		uiModel.addAttribute("prImages", list);

		// 轮播图
		List<PrImage> listSwipers = imageService.selectByProductId(id,
				PrImageType.SWIPER);

		// 轮播图
		List<PrImage> listDetails = imageService.selectByProductId(id,
				PrImageType.DETAIL);
		uiModel.addAttribute("detailImages", listDetails);
		uiModel.addAttribute("swiperImages", listSwipers);

		uiModel.addAttribute("product", product);
		uiModel.addAttribute("operation", "edit");

		return basePath + "product_edit";
	}

	@RequiresPermissions("mall:product:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute PrProductWithBLOBs product,
			@RequestParam String propertyValues,
			@RequestParam String propertyPrices, BindingResult result,
			Model uiModel, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/product/create";
		}
		if ("".equals(product.getCategoryId())) {
			product.setCategoryId(null);
		}

		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			TbUser user = (TbUser) currentUser.getSession()
					.getAttribute("user");
			if (user != null) {
				product.setCreator(user.getUsername());
				product.setUserId(user.getId());
				ShopWithBLOBs shop = shopService.selectByUserId(user.getId());
				product.setShopId(shop.getId());
				product.setEnName(shop.getName());
			}
		}

		int res = productService.insertSelective(product);
		if (res == 1) {
			// 更新轮播图。
			redirectAttributes.addFlashAttribute("message", "保存成功！");
			imageService.updatePrImageRealPId(product.getId());

			if (null != product.getContent()) {
				String cont = product.getContent();
				// 详情图
				List<PrImage> listDetails = imageService.selectByProductId(
						product.getId(), PrImageType.DETAIL);
				// 不用的详情图找出来，并且要把它们删除。
				List<String> idsNotInUse = new ArrayList<String>();
				for (PrImage prImage : listDetails) {
					if (cont.indexOf(prImage.getId()) == -1) {
						idsNotInUse.add(prImage.getId());
					}
				}
				if (idsNotInUse.size() > 0) {
					this.imageService.deleteBatch(idsNotInUse);
				}
			}
		}
		JSONArray perpertyArray = null;
		perpertyArray = (JSONArray) JSONArray.parse(propertyValues);
		for (Object object : perpertyArray) {
			JSONObject jsonObject = (JSONObject) object;
			PrProductPropertyValue productProperty = new PrProductPropertyValue();
			productProperty.setId(jsonObject.getString("id"));
			productProperty.setProductId(product.getId());
			productProperty.setPropertyId(jsonObject
					.getString("propertyId"));
			productProperty.setName((String) jsonObject.get("name"));
			productPropertyValueService.insertSelective(productProperty);
		}
		if (null != propertyValues && !"".equals(propertyValues)) {
			
		}

		JSONArray perPriceArray = null;
		
		perPriceArray = (JSONArray) JSONArray.parse(propertyPrices);
		for (Object obj : perPriceArray) {
			JSONObject json = (JSONObject) obj;
			PrProductPackagePrice packagePrice = new PrProductPackagePrice();
			String id = json.getString("id");
			String groupPrice = json.getString("groupPrice");
			String indepentPrice = json.getString("indepentPrice");
			String pId = product.getId();
			String pValueId = json.getString("propertyValueId");
			String icon = json.getString("icon");
			String disable = json.getString("disable");
			packagePrice.setIcon(icon);
			packagePrice.setDisable(Byte.valueOf(disable));
			packagePrice.setId(id);
			packagePrice.setGourpPrice(groupPrice);
			packagePrice.setIndependentPrice(indepentPrice);
			packagePrice.setProductId(pId);
			packagePrice.setPropertyValueId(pValueId);
			packagePriceService.insertSelective(packagePrice);
		}
		if (null != propertyPrices && !"".equals(propertyPrices)) {
			
		}
		// redirectAttributes.addFlashAttribute("propertyValues",
		// propertyValues);
		// redirectAttributes.addFlashAttribute("propertyPrices",
		// propertyPrices);
		return "redirect:/product/create";
	}

	@RequiresPermissions("mall:product:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute PrProductWithBLOBs product,
			@RequestParam String propertyValues,
			@RequestParam String propertyPrices, BindingResult result,
			Model uiModel, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if ("".equals(product.getCategoryId())) {
			product.setCategoryId(null);
		}

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}

		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			TbUser user = (TbUser) currentUser.getSession()
					.getAttribute("user");
			if (user != null) {
				product.setUpdater(user.getUsername());
				product.setUserId(user.getId());
			}
		}

		JSONArray perpertyArray = null;
		List<PrProductPropertyValue> productPropertyValues = this.productPropertyValueService
				.selectByProductId(product.getId());

		perpertyArray = (JSONArray) JSONArray.parse(propertyValues);
		if(perpertyArray==null){
			perpertyArray = new JSONArray();
		}
		productPropertyValueService.deleteByProductId(product.getId());

		for (Object object : perpertyArray) {
			JSONObject jsonObject = (JSONObject) object;
			PrProductPropertyValue productProperty = new PrProductPropertyValue();
			productProperty.setId(jsonObject.getString("id"));
			productProperty.setProductId(product.getId());
			productProperty.setImageId(jsonObject.getString("imageId"));
			productProperty.setPropertyId(jsonObject
					.getString("propertyId"));
			productProperty.setName((String) jsonObject.get("name"));
			productPropertyValueService
					.insertSelective(productProperty);
		}
		
		if (null != propertyValues && !"".equals(propertyValues)) {
			boolean reConnect = false;
			

			for (int i = 0; i < perpertyArray.size(); i++) {
				JSONObject object = (JSONObject) perpertyArray.get(i);
				String id = object.getString("id");
				boolean flag = false;
				for (PrProductPropertyValue propertyValue : productPropertyValues) {
					if (id.equals(propertyValue.getId())) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					reConnect = true;
					break;
				}
			}
			reConnect = true;
			// 重新建立关联关系
			if (reConnect == true) {
				
			}
		}

		JSONArray perPriceArray = null;
		perPriceArray = (JSONArray) JSONArray.parse(propertyPrices);
		if(perPriceArray==null){
			perPriceArray = new JSONArray();
		}
		List<PrProductPackagePrice> packagePrices = packagePriceService
				.selectByProductId(product.getId());
		packagePriceService.deleteByProductId(product.getId());
		for (Object obj : perPriceArray) {
			JSONObject json = (JSONObject) obj;
			PrProductPackagePrice packagePrice = new PrProductPackagePrice();
			String id = json.getString("id");
			String uuid = UUID.randomUUID().toString()
					.replaceAll("-", "");
			id = uuid;
			String groupPrice = json.getString("groupPrice");
			String indepentPrice = json.getString("indepentPrice");
			String pId = product.getId();
			String pValueId = json.getString("propertyValueId");
			String icon = json.getString("icon");
//			String disable = json.getString("disable");
			String disable = "0";
			packagePrice.setIcon(icon);
			packagePrice.setDisable(Byte.valueOf(disable));
			packagePrice.setId(id);
			packagePrice.setGourpPrice(groupPrice);
			packagePrice.setIndependentPrice(indepentPrice);
			packagePrice.setProductId(pId);
			packagePrice.setPropertyValueId(pValueId);
			packagePriceService.insertSelective(packagePrice);
		}
		
		/*
		if (null != propertyPrices && !"".equals(propertyPrices)) {
			boolean reConnect = false;
			
			
			if (perPriceArray.size() == packagePrices.size()) {
				for (int i = 0; i < perPriceArray.size(); i++) {
					JSONObject object = (JSONObject) perPriceArray.get(i);
					String id = object.getString("id");
					boolean flag = false;
					for (PrProductPackagePrice prProductPackagePrice : packagePrices) {
						if (id.equals(prProductPackagePrice.getId())) {
							flag = true;
							break;
						}
					}
					if (flag == false) {
						reConnect = true;
						break;
					}
				}
				if(reConnect == false){
				for (PrProductPackagePrice prProductPackagePrice : packagePrices) {
					boolean flag = false;
					for(int i = 0; i < perPriceArray.size(); i++) {
						JSONObject object = (JSONObject) perPriceArray.get(i);
						String id = object.getString("id");
						if (id.equals(prProductPackagePrice.getId())) {
							flag = true;
							break;
						}
					}
					if (flag == false) {
						reConnect = true;
						break;
					}
				}}
			}

			// 检查属性有没有改变。
			if (reConnect == false) {
				for (int i = 0; i < perPriceArray.size(); i++) {
					JSONObject object = (JSONObject) perPriceArray.get(i);
					String id = object.getString("id");
					for (PrProductPackagePrice prProductPackagePrice : packagePrices) {
						if (id.equals(prProductPackagePrice.getId())) {
							boolean changed = false;
							String gourpPrice = object.getString("gourpPrice");
							String independentPrice = object
									.getString("independentPrice");
							if (!gourpPrice.equals(prProductPackagePrice
									.getGourpPrice())) {
								changed = true;
								prProductPackagePrice.setGourpPrice(gourpPrice);
							}
							if (!independentPrice.equals(prProductPackagePrice
									.getIndependentPrice().toString())) {
								changed = true;
								prProductPackagePrice
										.setIndependentPrice(independentPrice);
							}
							if (changed == true) {
								packagePriceService
										.updateByPrimaryKey(prProductPackagePrice);
							}
							break;
						}
					}
				}
			}
			reConnect = true;
			// 重新建立关联关系
			if (reConnect == true) {
				
			}
		}*/

		int res = this.productService.updateByPrimaryKeySelective(product);
		if (res == 1) {
			redirectAttributes.addFlashAttribute("message", "保存成功！");
			imageService.updatePrImageRealPId(product.getId());

			if (null != product.getContent()) {
				String cont = product.getContent();
				// 轮播图
				List<PrImage> listDetails = imageService.selectByProductId(
						product.getId(), PrImageType.DETAIL);
				// 不用的详情图找出来，并且要把它们删除。
				List<String> idsNotInUse = new ArrayList<String>();
				for (PrImage prImage : listDetails) {
					if (cont.indexOf(prImage.getId()) == -1) {
						idsNotInUse.add(prImage.getId());
					}
				}
				if (idsNotInUse.size() > 0) {
					this.imageService.deleteBatch(idsNotInUse);
				}

			}
		}

		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/product/edit/" + product.getId();
	}
}
