package com.zwo.modules.zhihuiduo.web;

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

import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

/**
 * 会员登录控制器。
 * @author 黄记新 2017.8.8
 *
 */
@Controller
@Lazy(true)
public class IndexController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/member/";
	
	  
	@RequestMapping(value = {"mindex"},method=RequestMethod.GET)
	public String index(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		if(redisTemplate!= null){
			ListOperations<String, List> listOpe =  redisTemplate.opsForList();
		}
		uiModel.addAttribute("rawData", 123456);
		return basePath+"index";
	}
	
}
