package com.zwo.modules.zhihuiduo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.ProductStatus;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.zhihuiduo.dto.MainData;
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
public class IndexController extends BaseController {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	private static final String basePath = "views/member/";

	@RequestMapping(value = { "mindex" }, method = RequestMethod.GET)
	public String index(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (redisTemplate != null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
		List<PrProduct> list = null;
		list = prductService.selectAllByStatus(ProductStatus.ONLINE);
		if (redisTemplate != null) {
			ListOperations<String, List> listOpe = redisTemplate.opsForList();
		}
		List<GroupPurcse> groupPurcses = new ArrayList<GroupPurcse>();
		for (int i = 0; i < 10; i++) {
			GroupPurcse groupPurcseMember  = new GroupPurcse();
			groupPurcseMember.setProductId("c6dd09184e7d4f8ab7b9d96edbaf3595");
			groupPurcseMember.setMemberName("田间青青");
			groupPurcseMember.setMemberIcon("http://wx.qlogo.cn/mmopen/z2JphiaeYdpf3r6Lxr1icDaiajHGGSI5SkOOtGbIJo5h8eSt2NiaBHtpWOpuvsrPpCRtZKov3iacScRyicErrzEUlHcrvibNfRlMRae/0");
			groupPurcses.add(groupPurcseMember);
		}
		
		
		MainData data = new MainData();
		data.setGroupPurcses(groupPurcses);
		data.setProducts(list);
		String rawData = JSONObject.toJSONString(data);
//		uiModel.addAttribute("list", list);
		uiModel.addAttribute("rawData", rawData);
		return basePath + "index";
	}

}
