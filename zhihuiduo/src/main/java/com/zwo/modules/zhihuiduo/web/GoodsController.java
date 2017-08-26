package com.zwo.modules.zhihuiduo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.zhihuiduo.dto.MemberInfo;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

/**
 * 会员登录控制器。
 * @author 黄记新 2017.8.8
 *
 */
@Controller
@Lazy(true)
public class GoodsController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	@Autowired
	@Lazy(true)
	private IShopService shopService;
	@Autowired
	@Lazy(true)
	private IShopCategoryService shopCategoryService;
	
//	@SuppressWarnings("rawtypes")
//	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/goods/";
	
	
	/**
	 * 商品具体信息。
	 * @param goodsId
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = {"goodsDetail"},method=RequestMethod.GET)  
	public String goodsDetail(@RequestParam String goodsId,Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		PrProductWithBLOBs product = prductService.selectByPrimKey(goodsId);
		if(product!=null){
			if(null != product.getUserId()){
				//查询店铺信息。
				Shop shop = shopService.selectByUserId(product.getUserId());
				uiModel.addAttribute("shop", shop);
				if(shop != null){
					//查询店铺所有的商品，统一用goods。
					List<PrProduct> goodsList =  shopService.selectPrProductsByShopId(shop.getId());
					uiModel.addAttribute("goodsList", goodsList);
				}
				
			}
			
			//商品轮播图。
			List<PrImage> prImages =  prductService.selectByProductId(product.getId(),true);
			uiModel.addAttribute("swiperImages", prImages);
		}
		
		/*if(redisTemplate!= null){
			ListOperations<String, List> listOpe =  redisTemplate.opsForList();
		}*/
		uiModel.addAttribute("goods", product);
		return basePath+"goodsDetail";
	}
	
}
