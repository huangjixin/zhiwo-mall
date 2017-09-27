/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.zwo.modules.mall.domain.PrProduct;

/**
 * @author Administrator
 *
 */
@Service
public class PrProductUpdateMessageListener implements MessageListener {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/*
	 * 当商品分类新增或者更新的时候，重新生成顶层菜单。 (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		RedisSerializer<?> stringSerializer = redisTemplate.getStringSerializer();
		RedisSerializer<?> valueSerializer = redisTemplate.getDefaultSerializer();
		Object channel = stringSerializer.deserialize(message.getChannel());
		Object body = valueSerializer.deserialize(message.getBody());
		PrProduct product = null;
		if(body instanceof PrProduct){
			product = (PrProduct) body;
			//更新整个商品JSP；
			String path = System.getProperty("webapp.root");
			path += File.separator+"WEB-INF" + File.separator+"views"+ File.separator+"goods";
			String templateName = "goodsDetail.ftl";
//			String fileName = product.getId()+".jsp";
			//Map root = new HashMap<>();
			//FreeMarkerUtil.analysisTemplate(templatePath, templateName, fileName, root);
		}
//		System.out.println("主题: " + channel);
//		System.out.println("消息内容的类型是PrCategory？ " + (body instanceof PrCategory));
	}

	public static void main(String[] args) {
		String path = PrProductUpdateMessageListener.class.getResource("/").getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);
		
	}
}
