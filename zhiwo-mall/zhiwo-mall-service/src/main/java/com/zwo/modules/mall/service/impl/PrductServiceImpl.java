/**
 * 
 */
package com.zwo.modules.mall.service.impl;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.github.pagehelper.PageInfo2;
import com.zwo.modules.mall.dao.PrProductMapper;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PrductServiceImpl extends BaseService<PrProduct> implements IPrductService {
	private static Logger logger = LoggerFactory.getLogger(PrductServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductServiceImpl服务类提供的基础操作增删改查等】";
	
	@Autowired
	@Lazy(true)
	private PrProductMapper prProductMapper;
	
	@Override
	public Mapper<PrProduct> getBaseMapper() {
		return prProductMapper;
	}
	
	@Lazy(true)
	@Autowired
    private Destination mallProductQueueDestination;  
	
	@Lazy(true)
	@Autowired 
	private JmsTemplate jmsTemplate; 
	
	/* (non-Javadoc)
	 * @see com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*@Override
	public int insertBatch(List<PrProduct> list) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/* (non-Javadoc)
	 * @see com.zwotech.modules.core.service.IBaseService#countByExample(java.lang.Object)
	 */
	/*@Override
	public int countByExample(Object example) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/* (non-Javadoc)
	 * @see com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.Object)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	@CacheEvict(value = "PrProduct", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = prProductMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "PrProduct", allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrProductCriteria prProductCriteria = new PrProductCriteria();
		prProductCriteria.createCriteria().andIdIn(list);
		int result = prProductMapper.deleteByExample(prProductCriteria);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@CacheEvict(value = "PrProduct", allEntries = true)
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = super.deleteByPrimaryKey(id);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insert(java.lang.Object)
	 */
	@Override
	@CachePut(value = "PrProduct", key = "#record.id")
	public int insert(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insert(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertSelective(java.lang.
	 * Object)
	 */

	@Override
	@CachePut(value = "PrProduct", key = "#record.id")
	public int insertSelective(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "ACTIVEMQ发送创建完成消息，消息体为(0代表新增，1代表删除，2代表修改):0:"+record.getId());
		if(jmsTemplate!= null && mallProductQueueDestination!= null){
			sendMessage(record.getId());
		}
		
		 if (logger.isInfoEnabled())
				logger.info(BASE_MESSAGE + "ACTIVEMQ发送创建完成消息结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByExample(java.lang.
	 * Object)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PrProduct> selectByExample(Object example) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id", value = "PrProduct")
	@Transactional(readOnly = true)
	public PrProduct selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrProduct product = super.selectByPrimaryKey(id);
		if(null!=product && null!=product.getContent() && !"".equals(product.getContent())){
			String content = product.getContent();
			content = HtmlUtils.htmlUnescape(content);
			product.setContent(content);
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "PrProduct", allEntries = true)
	@Override
	public int updateByExampleSelective(PrProduct record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = super.updateByExampleSelective(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "PrProduct", allEntries = true)
	public int updateByExample(PrProduct record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}						
		//逻辑操作		
		int result = super.updateByExample(record, example);
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKeySelective
	 * (java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "PrProduct", allEntries = true)
	public int updateByPrimaryKeySelective(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = super.updateByPrimaryKeySelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKey(java.
	 * lang.Object)
	 */
	@Override
	@CachePut(value = "PrProduct", key = "#record.id")
	public int updateByPrimaryKey(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		if(null!=record.getContent() && "".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = super.updateByPrimaryKey(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPageInfo(java.lang.
	 * Object, com.github.pagehelper.PageInfo)
	 */
	@Transactional(readOnly = true)
	@Override
	public PageInfo2<PrProduct> selectByPageInfo(Object example, PageInfo2<PrProduct> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}
	
	public void sendMessage(final String msg) {  
        jmsTemplate.send("mall.product.queue", new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
            	TextMessage message =session.createTextMessage();
            	message.setText(msg);
            	return message;
            }  
        });  
    }

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");//此文件放在SRC目录下
		IPrductService prductService = (IPrductService)context.getBean("prductServiceImpl");
		PrProduct product = new PrProduct();
		product.setId(System.currentTimeMillis()+"");
		int result = prductService.insertSelective(product);
		logger.info(result+"");
	}

}
