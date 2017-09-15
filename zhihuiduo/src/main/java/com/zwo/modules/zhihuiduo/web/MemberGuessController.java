package com.zwo.modules.zhihuiduo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.domain.MemberPlayAccountCriteria;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberPlayAccountService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.shop.service.IShopService;
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
	private IMemberPlayAccountService memberPlayAccountService;

	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;

	// @Autowired
	// @Lazy(true)
	// @SuppressWarnings("rawtypes")
	// private RedisTemplate redisTemplate = SpringContextHolder
	// .getBean("redisTemplate");

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
		List<GuessQuestionOption> list = guessQuestionService
				.selectIneffectQuestion();
		uiModel.addAttribute("list", list);
		return basePath + "guess";
	}

	/**
	 * 跳转到竞猜结算页面
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "guessCheckOut", method = RequestMethod.POST)
	@ResponseBody
	@RequiresAuthentication
	public String guessCheckOut(@RequestParam String bet,
			@RequestParam String questionId, @RequestParam String optionId,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		JSONObject jsonObject = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");

			if (member != null) {
				MemberPlayAccount memberPlayAccount = memberPlayAccountService
						.selectByPrimaryKey(member.getId());
				if (memberPlayAccount != null) {
					int account = memberPlayAccount.getZhihuidouCount();
					int be = Integer.valueOf(bet);
					if (account < be) { // 余额不足，应该返回余额不足的消息。
						jsonObject.put("result", 0);
						jsonObject.put("message", "余额不足，请返回个人中心充值");
						return jsonObject.toJSONString();
					}
					try {
						GuessQuestion question = this.guessQuestionService.selectByPrimaryKey(questionId);
						int result = this.guessQuestionService.bet(memberPlayAccount, member.getId(), be, question,
								optionId);
						jsonObject.put("result", result);
						jsonObject.put("message", "下注成功");
						return jsonObject.toJSONString();
					} catch (Exception e) {
						return jsonObject.toJSONString();
					}
					
				}
				jsonObject.put("result", 0);
				jsonObject.put("message", "智慧豆账户没有创建，请联系管理人员");
				return jsonObject.toJSONString();
			}
			
			jsonObject.put("result", 0);
			jsonObject.put("message", "请登录");
			return jsonObject.toJSONString();
		}
		jsonObject.put("result", 0);
		jsonObject.put("message", "请登录");
		return jsonObject.toJSONString();
	}

}
