/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwotech.common.utils.FreeMarkerUtil;
import com.zwotech.common.utils.SpringContextHolder;

/**
 * @author Administrator
 *
 */
@Service
@Lazy(true)
public class MemberGuessGenerationMessageListener implements MessageListener {
	
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;
	
	/*
	 * 当竞猜问题新增或者更新的时候，重新生成静态页面。 (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.connection.MessageListener#onMessage(org.
	 * springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		if(redisTemplate == null ){ 
			redisTemplate = SpringContextHolder.getBean("redisTemplate"); 
		}
		
		if(redisTemplate != null){
			List<GuessQuestionOption> list = guessQuestionService.selectIneffectQuestion();
			
			String path = System.getProperty("webapp.root");
			path += File.separator+"WEB-INF" + File.separator+"views"+ File.separator+"member";
			String templateName = "guess.ftl";
			String fileName = "guess.htm";
			Map root = new HashMap<>();
			root.put("", list);
			FreeMarkerUtil.analysisTemplate(path, templateName, fileName, root);
			
			RedisSerializer<?> stringSerializer = redisTemplate.getStringSerializer();
			RedisSerializer<?> valueSerializer = redisTemplate.getDefaultSerializer();
			Object channel = stringSerializer.deserialize(message.getChannel());
			Object body = valueSerializer.deserialize(message.getBody());
			
		}
		
		
	}

	public static void main(String[] args) {
		String path = MemberGuessGenerationMessageListener.class.getResource("/").getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);
		
	}
}
