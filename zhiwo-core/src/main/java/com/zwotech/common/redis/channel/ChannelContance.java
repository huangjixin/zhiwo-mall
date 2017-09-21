/**
 * 
 */
package com.zwotech.common.redis.channel;

/**
 * @author 黄记新
 *
 */
public class ChannelContance {
	/**
	 * 竞猜页面生成订阅频道。
	 */
	public static final String GUESS_QUESTION_GENERATION_TOPIC_CHANNEL = "guess_question_generation.topic.channel";

	/**
	 * 商品页面生成订阅频道。
	 */
	public static final String PRODUCT_GENERATION_TOPIC_CHANNEL = "product_generation.topic.channel";
	/**
	 * 商品图删除订阅频道。
	 */
	public static final String PRIMAGE_DELETE_TOPIC_CHANNEL = "primage_delete.topic.channel";

	/**
	 * 用户关注公众号产生创建新用户。
	 */
	public static final String MEMBER_CREATE_QUEUE_CHANNEL = "member_create.queue.channel";

	/**
	 * 用户关注公众号产生更新用户。
	 */
	public static final String MEMBER_UPDATE_QUEUE_CHANNEL = "member_update.queue.channel";

	/**
	 * 用户下订单。
	 */
	public static final String ORDER_CREATE_QUEUE_CHANNEL = "order_create.queue.channel";

	/**
	 * 用户开团。
	 */
	public static final String GROUPPURCSE_CREATE_QUEUE_CHANNEL = "groupPurcse_create.queue.channel";
}
