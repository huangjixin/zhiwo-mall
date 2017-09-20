package com.zwo.modules.wechat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwotech.common.utils.SignUtil;

@Controller
@RequestMapping("wechat")
public class WeChatController {
	private static final String TOKEN = "huangjixinWeixinToken";

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String validateWechat(@RequestParam String signature,
			@RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam String echostr, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if(SignUtil.checkSignature(TOKEN, signature, timestamp, nonce)){
			return echostr;
		}
		
		return "error";
	}
}
