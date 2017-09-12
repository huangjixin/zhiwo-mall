/**
 * 
 */
package com.zwo.modules.mall.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.PrImageMapper;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageCriteria;
import com.zwo.modules.mall.domain.PrImageType;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PrImageServiceImpl extends BaseService<PrImage> implements
		IPrImageService {
	private static Logger logger = LoggerFactory
			.getLogger(PrImageServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrImageServiceImpl服务类提供的基础操作增删改查等】";
	private static final String KEY_TYPE_THUMBNAIL = "_key_thumbnail_prImages";
	private static final String KEY_TYPE_DETAIL = "_key_detail_prImages";
	private static final String KEY_TYPE_PROP = "_key_prop_prImages";
	private static final String KEY_TYPE_SWIPER = "_key_swiper_prImages";
	@Autowired
	@Lazy(true)
	private PrImageMapper prImageMapper;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	@Override
	public Mapper<PrImage> getBaseMapper() {
		return prImageMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrImage> list) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#countByExample(java.lang.
	 * Object)
	 */
	/*
	 * @Override public int countByExample(Object example) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	@CacheEvict(value = "PrImage", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<PrImage> list = this.prImageMapper.selectByExample(example);
		if (list != null) {
			for (PrImage image : list) {
				String key = image.getProductId()+KEY_TYPE_DETAIL;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_PROP;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_SWIPER;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_THUMBNAIL;
				removeRedisKey(key);
				

				if (image.getLocation() != null
						&& !"".equals(image.getLocation())) {
					Path target = Paths.get(image.getLocation());
					try {
						if (Files.exists(target))
							Files.deleteIfExists(target);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// 逻辑操作
		int result = prImageMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "PrImage", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrImageCriteria prImageCriteria = new PrImageCriteria();
		prImageCriteria.createCriteria().andIdIn(list);
		List<PrImage> images = this.prImageMapper.selectByExample(prImageCriteria);
		if (images != null && !images.isEmpty()) {
			for (PrImage image : images) {
				String key = image.getProductId()+KEY_TYPE_DETAIL;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_PROP;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_SWIPER;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_THUMBNAIL;
				removeRedisKey(key);

				if (image.getLocation() != null
						&& !"".equals(image.getLocation())) {
					Path target = Paths.get(image.getLocation());
					try {
						if (Files.exists(target))
							Files.deleteIfExists(target);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(redisTemplate!=null){
					redisTemplate.convertAndSend(ChannelContance.PRIMAGE_DELETE_TOPIC_CHANNEL, image);
				}
			}
		}

		int result = prImageMapper.deleteByExample(prImageCriteria);

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
	@CacheEvict(value = "PrImage", key = "#id+'PrImage'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为："
					+ id.toString());
		PrImage image = this.selectByPrimaryKey(id);
		String key = image.getProductId()+KEY_TYPE_DETAIL;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_PROP;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_SWIPER;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_THUMBNAIL;
		removeRedisKey(key);
		

		if (image.getLocation() != null && !"".equals(image.getLocation())) {
			Path target = Paths.get(image.getLocation());
			try {
				if (Files.exists(target))
					Files.deleteIfExists(target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 逻辑操作
		int result = super.deleteByPrimaryKey(id);
		if(redisTemplate!=null){
			redisTemplate.convertAndSend(ChannelContance.PRIMAGE_DELETE_TOPIC_CHANNEL, image);
		}
		
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
	// @CachePut(value = "PrImage", key = "#record.id+'_PrImage'")
	public int insert(PrImage record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = super.insert(record);
		
		String key = record.getProductId()+KEY_TYPE_DETAIL;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_PROP;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_SWIPER;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_THUMBNAIL;
		removeRedisKey(key);
		
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
	// @CachePut(value = "PrImage", key = "#record.id+'_PrImage'")
	public int insertSelective(PrImage record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		String key = record.getProductId()+KEY_TYPE_DETAIL;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_PROP;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_SWIPER;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_THUMBNAIL;
		removeRedisKey(key);

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = super.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
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
	public List<PrImage> selectByExample(Object example) {
		return prImageMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'PrImage'", value = "PrImage")
	@Transactional(readOnly = true)
	public PrImage selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrImage prImage = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return prImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "PrImage", allEntries = true)
	@Override
	public int updateByExampleSelective(PrImage record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为："
					+ record.toString());
			List<PrImage> prImages = this.prImageMapper.selectByExample(example);
			for (PrImage image : prImages) {
				String key = image.getProductId()+KEY_TYPE_DETAIL;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_PROP;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_SWIPER;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_THUMBNAIL;
				removeRedisKey(key);
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
	@CacheEvict(value = "PrImage", allEntries = true)
	public int updateByExample(PrImage record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为："
					+ record.toString());
		
			List<PrImage> prImages = this.prImageMapper.selectByExample(example);
			for (PrImage image : prImages) {
				String key = image.getProductId()+KEY_TYPE_DETAIL;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_PROP;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_SWIPER;
				removeRedisKey(key);
				key = image.getProductId()+KEY_TYPE_THUMBNAIL;
				removeRedisKey(key);
		}
		// 逻辑操作
		int result = super.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
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
	@CacheEvict(value = "PrImage", key = "#record.id+'_PrImage'")
	public int updateByPrimaryKeySelective(PrImage image) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为："
					+ image.toString());
		String key = image.getProductId()+KEY_TYPE_DETAIL;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_PROP;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_SWIPER;
		removeRedisKey(key);
		key = image.getProductId()+KEY_TYPE_THUMBNAIL;
		removeRedisKey(key);
		// 逻辑操作
		int result = super.updateByPrimaryKeySelective(image);
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
	@CacheEvict(value = "PrImage", key = "#record.id+'_PrImage'")
	public int updateByPrimaryKey(PrImage record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为："
					+ record.toString());

		String key = record.getProductId()+KEY_TYPE_DETAIL;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_PROP;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_SWIPER;
		removeRedisKey(key);
		key = record.getProductId()+KEY_TYPE_THUMBNAIL;
		removeRedisKey(key);

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
	public PageInfo<PrImage> selectByPageInfo(Object example,
			PageInfo<PrImage> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/mall-applicationContext.xml");// 此文件放在SRC目录下
		IPrImageService prImageServiceImpl = (IPrImageService) context
				.getBean("prImageServiceImpl");
		PrImage prImage = new PrImage();
		prImage.setId(System.currentTimeMillis() + "");
		int result = prImageServiceImpl.insertSelective(prImage);
		logger.info(result + "");
	}

	@Override
	public int deletePrImageByProductId(String productId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品ID删除开始");

		PrImageCriteria imageCriteria = new PrImageCriteria();
		imageCriteria.createCriteria().andProductIdEqualTo(productId);
		List<PrImage> list = this.prImageMapper.selectByExample(imageCriteria);
		for (PrImage prImage : list) {
			String key = prImage.getProductId()+KEY_TYPE_DETAIL;
			removeRedisKey(key);
			key = prImage.getProductId()+KEY_TYPE_PROP;
			removeRedisKey(key);
			key = prImage.getProductId()+KEY_TYPE_SWIPER;
			removeRedisKey(key);
			key = prImage.getProductId()+KEY_TYPE_THUMBNAIL;
			removeRedisKey(key);
		}

		int result = this.deleteByExample(imageCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品ID删除结束，结果为" + result);
		return result;
	}

	@Cacheable(key = "#productId+'_key_'+#type+'_prImages'", value = "PrProductPrImage")
	@Transactional(readOnly = true)
	@Override
	public List<PrImage> selectByProductId(String productId, String type) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByProductId根据商品ID查询图片开始,商品ID是："
					+ productId + ",图片类型是：" + type);

		PrImageCriteria imageCriteria = new PrImageCriteria();
		imageCriteria.createCriteria().andProductIdEqualTo(productId)
				.andTypeEqualTo(type);
		imageCriteria.setOrderByClause("id desc");
		List<PrImage> list = this.prImageMapper.selectByExample(imageCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByProductId根据商品ID查询图片结束，结果条目数："
					+ list.size());
		return list;
	}

	@Override
	public int updatePrImageRealPId(String productId) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updatePrImageRealPId更新商品图片为真实的外键开始,商品ID是："
					+ productId);

		PrImageCriteria imageCriteria = new PrImageCriteria();
		imageCriteria.createCriteria().andProductIdEqualTo(productId)
				.andRealProductIdIsNull();
		PrImage record = new PrImage();
		record.setProductId(productId);
		record.setRealProductId(productId);
		int result = this.updateByExampleSelective(record, imageCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updatePrImageRealPId更新商品图片为真实的外键结束，结果条目数："
					+ result);
		return result;
	}
	
	/**
	 * 移除key.
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	private void removeRedisKey(String key){
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder
					.getBean("redisTemplate");
		}
		
		if (redisTemplate != null) {
			if (redisTemplate.hasKey(key)) {
				redisTemplate.delete(key);
			}
		}
	}
}
