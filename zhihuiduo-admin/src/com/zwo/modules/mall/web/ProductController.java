package com.zwo.modules.mall.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.restlet.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	public ProductController() {
		super();
		if (redisTemplate == null) {
			if (SpringContextHolder.getApplicationContext().containsBean(
					"redisTemplate"))
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}

	/**
	 * 默认执行方法。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping()
	String defaultMethod(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return list(httpServletRequest, httpServletResponse, null, null);
	}

	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = { "list" })
	public String list(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel,
			@ModelAttribute PrProduct product) {
		if(product!=null)
		uiModel.addAttribute("product", product);
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

	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,
			@RequestParam(required = false) String propertyValues,
			@RequestParam(required = false) String propertyPrices,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		PrProduct product = productService.selectByPrimaryKey(id);

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

		// 详情图图
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
			productProperty.setPropertyId(jsonObject.getString("propertyId"));
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

		// 是否重新建立关联关系。
		boolean reConnect = false;
		// 优先判断有没有属性值，如果都没有那不用重新建立
		if (perpertyArray == null && productPropertyValues.size() == 0) {
			reConnect = false;
		} else if (perpertyArray != null && productPropertyValues.size() == 0) {
			reConnect = true;
		} else {
			if (perpertyArray != null) {
				int length = perpertyArray.size();
				for (int i = 0; i < length; i++) {
					JSONObject jsonObject = (JSONObject) perpertyArray.get(i);
					String id = jsonObject.getString("id");
					int length1 = productPropertyValues.size();
					boolean flag = false;
					for (int j = 0; j < length1; j++) {
						PrProductPropertyValue propertyValue = productPropertyValues
								.get(j);
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

				if (reConnect == false) {
					length = productPropertyValues.size();
					for (int i = 0; i < length; i++) {
						PrProductPropertyValue propertyValue = productPropertyValues
								.get(i);
						int length1 = perpertyArray.size();
						boolean flag = false;
						for (int j = 0; j < length1; j++) {
							JSONObject jsonObject = (JSONObject) perpertyArray
									.get(j);
							String id = jsonObject.getString("id");
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
				}
			} else {
				reConnect = true;
			}
		}

		if (reConnect == true) {
			productPropertyValueService.deleteByProductId(product.getId());
			if (perpertyArray != null) {
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
			}
		}

		// 价格组合
		// //////////
		JSONArray perPriceArray = null;
		perPriceArray = (JSONArray) JSONArray.parse(propertyPrices);
		List<PrProductPackagePrice> packagePrices = packagePriceService
				.selectByProductId(product.getId());
		if (perPriceArray == null && packagePrices.size() == 0) {
			reConnect = false;
		} else if (perPriceArray != null && packagePrices.size() == 0) {
			reConnect = true;
		} else {
			if (perPriceArray != null) {
				int length = perPriceArray.size();
				for (int i = 0; i < length; i++) {
					JSONObject jsonObject = (JSONObject) perPriceArray.get(i);
					String id = jsonObject.getString("id");
					int length1 = packagePrices.size();
					boolean flag = false;
					for (int j = 0; j < length1; j++) {
						PrProductPackagePrice packagePrice = packagePrices
								.get(j);
						if (id.equals(packagePrice.getId())) {
							flag = true;
							break;
						}
					}

					if (flag == false) {
						reConnect = true;
						break;
					}
				}

				if (reConnect == false) {
					length = packagePrices.size();
					for (int i = 0; i < length; i++) {
						PrProductPackagePrice packagePrice = packagePrices
								.get(i);
						int length1 = perpertyArray.size();
						boolean flag = false;
						for (int j = 0; j < length1; j++) {
							JSONObject jsonObject = (JSONObject) perPriceArray
									.get(j);
							String id = jsonObject.getString("id");
							if (id.equals(packagePrice.getId())) {
								flag = true;
								break;
							}
						}

						if (flag == false) {
							reConnect = true;
							break;
						}
					}
				}
			} else {
				reConnect = true;
			}

		}

		// 检查属性有没有改变。
		if (reConnect == false && perPriceArray != null) {
			for (int i = 0; i < perPriceArray.size(); i++) {
				JSONObject object = (JSONObject) perPriceArray.get(i);
				String id = object.getString("id");
				for (PrProductPackagePrice prProductPackagePrice : packagePrices) {
					if (id.equals(prProductPackagePrice.getId())) {
						boolean changed = false;
						String icon = object.getString("icon");
						String gourpPrice = object.getString("gourpPrice");
						String independentPrice = object
								.getString("independentPrice");
						if (!icon.equals(prProductPackagePrice.getIcon())) {
							changed = true;
							prProductPackagePrice.setIcon(icon);
						}
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

		if (reConnect == true) {
			packagePriceService.deleteByProductId(product.getId());
			if (perPriceArray != null) {
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
					// String disable = json.getString("disable");
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
			}
		}

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

	/**
	 * 从阿里巴巴批发网进行商品抓取。
	 * 
	 * @param url
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException 
	 */
	@RequiresPermissions("mall:product:create")
	@RequestMapping(value = "createFromAlibaba", method = RequestMethod.GET)
	public String createFromAlibaba(@RequestParam String url,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		PrProduct product = this.productService.fetchAlibabaGoods(url);
		return "redirect:/product/edit/"+product.getId();
	}

	//下载图片。
	private PrImage download(HttpServletRequest httpServletRequest,String productId, String type,
			String imgurl) {
		try {
			Calendar date = Calendar.getInstance();
			String rootDir = httpServletRequest.getSession().getServletContext().getRealPath("/");
			rootDir = "D:"+File.separator;
			String url = "passets/"+productId+"/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
					+ date.get(Calendar.DAY_OF_MONTH);
			String uploadPath = rootDir + "images" + File.separator + "passets";
			uploadPath = uploadPath+ File.separator+productId+ File.separator + date.get(Calendar.YEAR) + File.separator
					+ (date.get(Calendar.MONTH) + 1) + File.separator + date.get(Calendar.DAY_OF_MONTH);
			
			int index = imgurl.lastIndexOf(".");
			String datetimeName = new Date().getTime() + ((int) Math.random() * 10000) + "";
			String name =  datetimeName + imgurl.substring(index, imgurl.length());
			
			String imageName = uploadPath+File.separator+name;
			URL uri = new URL(imgurl);
			InputStream in = uri.openStream();
			File file = new File(uploadPath);
			if(!file.exists()){  
				file.mkdirs(); 
		    }  
			
			FileOutputStream fo = new FileOutputStream(file.getPath()+File.separator+name);
			byte[] buf = new byte[1024];
			int length = 0;
			System.out.println("开始下载:" + imgurl);
			while ((length = in.read(buf, 0, buf.length)) != -1) {
				fo.write(buf, 0, length);
			}
			in.close();
			fo.close();
			System.out.println(imageName + "下载完成");
			
			PrImage assets = new PrImage();
			assets.setType(type);
			assets.setIsDefault(false);
			assets.setProductId(productId);
			assets.setName(name);
			assets.setLocation(uploadPath + File.separator + name);
			assets.setUrl(url + "/" + name);
			assets.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			imageService.insertSelective(assets);
			return assets;
		} catch (Exception e) {
			System.out.println("下载失败");
		}
		return null;
	}
}
