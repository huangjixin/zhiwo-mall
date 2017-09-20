package com.zwo.modules.wechat.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;

import com.zwo.modules.wechat.dispatcher.EventDispatcher;
import com.zwo.modules.wechat.dispatcher.MsgDispatcher;
import com.zwo.modules.wechat.domain.WeChatVo;
import com.zwo.modules.wechat.util.MessageUtil;
import com.zwotech.common.utils.SignUtil;

@Controller
@RequestMapping("wechat")
public class WeChatController {
	private static final String TOKEN = "huangjixinWeixinToken";
	// 重复通知过滤
	private static ExpireKey expireKey = new DefaultExpireKey();

	private String encodingToken = ""; // Token(令牌) 通过微信后台获取
	private String encodingAesKey = ""; // EncodingAESKey(消息加解密密钥) 通过微信后台获取

	@Autowired
	@Lazy(true)
	private WeChatVo weChatVo;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String validateWechat(@RequestParam String signature,
			@RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam String echostr, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (SignUtil.checkSignature(TOKEN, signature, timestamp, nonce)) {
			return echostr;
		}
		// Token token = TokenAPI.token("appid", "secret");
		return "error";
	}

	@RequestMapping(method = RequestMethod.POST)
	// post方法用于接收微信服务端消息
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Map<String, String> map = MessageUtil.parseXml(request);
			String msgtype = map.get("MsgType");
			if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
				EventDispatcher.processEvent(map); // 进入事件处理
			} else {
				MsgDispatcher.processMessage(map); // 进入消息处理
			}
		} catch (Exception e) {
			
		}
	}

}
