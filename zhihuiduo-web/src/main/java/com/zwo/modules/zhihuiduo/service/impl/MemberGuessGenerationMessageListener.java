/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.zwotech.common.utils.PathUtil;
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
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}

		if (redisTemplate != null) {
			List<GuessQuestionOption> list = guessQuestionService
					.selectIneffectQuestion();
			String rootPath = PathUtil.getWebroot();
			
			String fileNamePath = rootPath +  "resources";
			
			String path = rootPath+"WEB-INF"+ "/views" + "/member/";
			String templateName = path + "guess.ftl";
			
			String fileName = fileNamePath + File.separator + "guess.htm";
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("list", list);
			FreeMarkerUtil.analysisTemplate(path, templateName, fileName, root);

			RedisSerializer<?> stringSerializer = redisTemplate
					.getStringSerializer();
			RedisSerializer<?> valueSerializer = redisTemplate
					.getDefaultSerializer();
			Object channel = stringSerializer.deserialize(message.getChannel());
			Object body = valueSerializer.deserialize(message.getBody());

		}

	}

	public static void main(String[] args) {
		String path = MemberGuessGenerationMessageListener.class.getResource(
				"/").getPath();
		System.out.println(path);
		path = System.getProperty("user.dir");
		System.out.println(path);
		path = System.getProperty("webapp.root");
		System.out.println(path);

	}
}
