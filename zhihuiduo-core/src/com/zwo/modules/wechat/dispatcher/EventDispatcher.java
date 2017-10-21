package com.zwo.modules.wechat.dispatcher;

import java.util.Date;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import weixin.popular.api.UserAPI;
import weixin.popular.bean.user.User;

import com.zwo.modules.wechat.domain.message.req.ImageMessage;
import com.zwo.modules.wechat.domain.message.resp.TextMessage;
import com.zwo.modules.wechat.util.MessageUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * ClassName: EventDispatcher
 * 
 * @Description: 事件消息业务分发器
 * @author dapengniao
 * @date 2016年3月7日 下午4:04:41
 */
public class EventDispatcher {

	private RedisTemplate redisTemplate = SpringContextHolder
			.getBean("redisTemplate");

	public static String processEvent(Map<String, String> map) {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID
//		User user = UserAPI
//				.userInfo(
//						"RPUJVApznSYnWmTFH_nxcIcSjl6dH2DMzl1nFL43caCME_5goDbWKonFxlikMZNf38RekAfi6jbncECgBbiEBwXf_LxHqIz6h5a3VrFE5-GByKXgi3KI2ALUFw7V0_nnYVOjAHADAT",
//						openid);
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			System.out.println("==============这是关注事件！");
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

		return null;
	}
}