/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

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
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.service.IGroupPurcseMemberService;
import com.zwo.modules.member.service.IGroupPurcseService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.member.service.impl.GroupPurcseMemberServiceImpl;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.zhihuiduo.dto.ProductExtention;
import com.zwotech.common.utils.FreeMarkerUtil;

/**
 * 创建或更新团购页面。
 * @author Administrator
 *
 */
@Service
public class MemberShopUpdateMessageListener implements MessageListener {
	@Autowired
	@Lazy(true)
	private RedisTemplate<String, String> redisTemplate;

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
	@Autowired
	@Lazy(true)
	private IGroupPurcseMemberService groupPurcseMemberService;

	/*
	 * 当商品分类新增或者更新的时候，重新生成顶层菜单。 (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		if(1==1)
			return;
		String jsonString=null;
		ProductExtention productExtention = null;
		
		RedisSerializer<?> stringSerializer = redisTemplate
				.getStringSerializer();
		RedisSerializer<?> valueSerializer = redisTemplate
				.getDefaultSerializer();
		Object channel = stringSerializer.deserialize(message.getChannel());
		Object body = valueSerializer.deserialize(message.getBody());

		if (body instanceof String) {
			String shopId = (String) body;
			productExtention = new ProductExtention();
			Shop shop = shopService.selectByPrimKey(shopId);
			if(shop==null){
				return;
			}
			List<PrProduct> products = prductService.selectPrProductsByShopId(shopId);
			productExtention.setShop(shop);
			productExtention.setGoodsList(products);
			
			//结果集。
			jsonString = JSONObject.toJSONString(productExtention, true);

			String cpath = this.getClass().getResource("/").getPath()
					.replaceFirst("/", "");
			String webappRoot = cpath.replaceAll("/WEB-INF/classes/", "");
			String templatePath = webappRoot + File.separator + "template";
			String templateName = "memberShop.ftl";
			String fileName = webappRoot + File.separator + "memberShop"
					+ File.separator + shopId + ".htm";
			
			Map root = new HashMap<>();
			root.put("rawData", jsonString);
			FreeMarkerUtil.analysisTemplate(templatePath, templateName,
					fileName, root);
		}
	}

	public static void main(String[] args) {
		String path = MemberShopUpdateMessageListener.class.getResource("/")
				.getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
