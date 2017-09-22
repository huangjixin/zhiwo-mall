package com.zwo.modules.wechat.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.wechat.dispatcher.EventDispatcher;
import com.zwo.modules.wechat.dispatcher.MsgDispatcher;
import com.zwo.modules.wechat.domain.WeChatVo;
import com.zwo.modules.wechat.domain.message.resp.TextMessage;
import com.zwo.modules.wechat.util.MessageUtil;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.ActiveMQUtil;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SignUtil;
import com.zwotech.common.utils.SpringContextHolder;

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

	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	private RedisTemplate redisTemplate;
	private JmsTemplate jmsQueueTemplate;

	public WeChatController() {
		super();
		redisTemplate = SpringContextHolder.getBean("redisTemplate");

		if (jmsQueueTemplate == null) {
			jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate");
		}
	}

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
	public String doPost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> map = MessageUtil.parseXml(request);
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID

		try {
			String msgtype = map.get("MsgType");
			if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
				EventDispatcher.processEvent(map); // 进入事件处理
				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
					System.out.println("==============这是关注事件！");
					Member member = memberService.selectByOpenId(openid);
					String content = member == null ? "欢迎你关注智惠多，请尽情购物吧！"
							: "欢迎你回来智惠多，请尽情购物吧！";
					// 普通文本消息
					TextMessage txtmsg = new TextMessage();
					txtmsg.setToUserName(openid);
					txtmsg.setFromUserName(mpid);
					txtmsg.setCreateTime(new Date().getTime());
					txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					txtmsg.setContent(content);

					Map<String, String> message = new HashMap<String, String>();
					message.put("openId", openid);
					if (member == null) {
						member = new Member();
						String id = UUID.randomUUID().toString()
								.replaceAll("-", "");
						String username = id.substring(0, 6);
						member.setId(id);
						member.setOpenId(openid);
						member.setUsername(username);
						member.setPassword(username);
					}
					ActiveMQUtil
							.send(jmsQueueTemplate,
									ChannelContance.MEMBER_CREATE_QUEUE_CHANNEL,
									member);
					/*
					 * RedisUtil.publish(redisTemplate,
					 * ChannelContance.MEMBER_CREATE_QUEUE_CHANNEL, message);
					 */
					return MessageUtil.textMessageToXml(txtmsg);
				}

				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
					System.out.println("==============这是取消关注事件！");
				}

				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
					System.out.println("==============这是扫描二维码事件！");
				}

				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
					System.out.println("==============这是位置上报事件！");
				}

				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
					System.out.println("==============这是自定义菜单点击事件！");
				}

				if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单View事件
					System.out.println("==============这是自定义菜单View事件！");
				}
			} else {
				MsgDispatcher.processMessage(map); // 进入消息处理
			}
		} catch (Exception e) {

		}

		return null;
	}

}
