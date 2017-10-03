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
public class MemberGroupUpdateMessageListener implements MessageListener {
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
		String jsonString = null;
		
		String goodsId = null;
		ProductExtention productExtention = null;
		List<GroupPurcseMember> groupPurcseMembers = null;
		GroupPurcse groupPurcse = null;
		PrProduct product = null;
		List<PrProductPackagePrice> packagePrices = null;
		List<PrProductPropertyValue> productPropertyValues = null;
		List<GroupPurcse> groupPurcses = null;
		
		RedisSerializer<?> stringSerializer = redisTemplate
				.getStringSerializer();
		RedisSerializer<?> valueSerializer = redisTemplate
				.getDefaultSerializer();
		Object channel = stringSerializer.deserialize(message.getChannel());
		Object body = valueSerializer.deserialize(message.getBody());

		if (body instanceof GroupPurcse) {
			GroupPurcse purcse = (GroupPurcse) body;
			String groupPurcseId = purcse.getId();
			
			groupPurcse = groupPurcseService.selectByPrimaryKey(groupPurcseId);
			if(groupPurcse!=null){
				goodsId = groupPurcse.getProductId();
			}
			// 商品属性。
			List<PrProductProperty> properties = productPropertyService.listAll();
			
			String key = groupPurcseId+GroupPurcseMemberServiceImpl.KEY_GROUP_PURCSE_ID_MEMBERS;
			
			productExtention = new ProductExtention();
			
			if(goodsId!=null){
				product = prductService.selectByPrimaryKey(goodsId);
				try {
					BeanUtils.copyProperties(productExtention, product);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				if(groupPurcse!=null && groupPurcse.getDisable()==false){
					groupPurcseMembers = groupPurcseMemberService.selectByGroupPurcseId(groupPurcse.getId());
					groupPurcses =groupPurcseService.selectGroupPurcseByPId(goodsId, false);
				}
				
				productExtention.setGroupPurcse(groupPurcse);
				packagePrices = packagePriceService.selectByProductId(product
						.getId());
				productPropertyValues = this.propertyValueService
						.selectByProductId(product.getId());
				productExtention.setPackagePrices(packagePrices);
				productExtention.setProductPropertyValues(productPropertyValues);
				productExtention.setGroupPurcseMembers(groupPurcseMembers);
				productExtention.setGroupPurcses(groupPurcses);
				productExtention.setProperties(properties);	
			}
			
			
			//结果集。
			jsonString = JSONObject.toJSONString(productExtention, true);

			String cpath = this.getClass().getResource("/").getPath()
					.replaceFirst("/", "");
			String webappRoot = cpath.replaceAll("/WEB-INF/classes/", "");
			String templatePath = webappRoot + File.separator + "template";
			String templateName = "memberGroup.ftl";
			String fileName = webappRoot + File.separator + "memberGroup"
					+ File.separator + product.getId() + ".htm";
			
			Map root = new HashMap<>();
			root.put("rawData", jsonString);
			FreeMarkerUtil.analysisTemplate(templatePath, templateName,
					fileName, root);
		}
	}

	public static void main(String[] args) {
		String path = MemberGroupUpdateMessageListener.class.getResource("/")
				.getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
