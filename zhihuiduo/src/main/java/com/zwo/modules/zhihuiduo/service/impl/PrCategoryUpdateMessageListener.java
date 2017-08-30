/**
 * 
 */
package com.zwo.modules.zhihuiduo.service.impl;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author Administrator
 *
 */
public class PrCategoryUpdateMessageListener implements MessageListener {

	/* 当商品分类新增或者更新的时候，重新生成顶层菜单。
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.connection.MessageListener#onMessage(org.springframework.data.redis.connection.Message, byte[])
	 */
	@Override
	public void onMessage( final Message message, final byte[] pattern ) {
		
	}

}
