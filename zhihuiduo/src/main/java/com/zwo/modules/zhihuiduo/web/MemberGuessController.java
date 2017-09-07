package com.zwo.modules.zhihuiduo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.shop.service.IShopService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping(value = { "memberGuess" })
@Lazy(true)
public class MemberGuessController extends BaseController {
	@Autowired
	@Lazy(true)
	private IOrderTradeService orderTradeService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IShopService shopService;
	@Autowired
	@Lazy(true)
	private IMemberAddressService addressService;
	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;

//	@Autowired
//	@Lazy(true)
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder
			.getBean("redisTemplate");

	private static final String basePath = "views/member/";

	/**
	 * 跳转到竞猜页面
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "guess")
	// @RequiresAuthentication
	public String guess(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<GuessQuestionOption> list = guessQuestionService.selectIneffectQuestion();
		uiModel.addAttribute("list",list);
		return basePath +"guess";
	}	
	
}
