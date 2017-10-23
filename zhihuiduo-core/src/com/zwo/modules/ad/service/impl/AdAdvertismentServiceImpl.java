/**
 * 
 */
package com.zwo.modules.ad.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import com.zwo.modules.ad.dao.AdAdvertismentMapper;
import com.zwo.modules.ad.dao.AdAssetsMapper;
import com.zwo.modules.ad.domain.AdAdvertisment;
import com.zwo.modules.ad.domain.AdAdvertismentCriteria;
import com.zwo.modules.ad.domain.AdAssets;
import com.zwo.modules.ad.domain.AdAssetsCriteria;
import com.zwo.modules.ad.service.IAdAdvertismentService;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

/**
 * @author 黄记新
 * 
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class AdAdvertismentServiceImpl extends BaseService<AdAdvertisment> implements IAdAdvertismentService {
	private static Logger logger = LoggerFactory.getLogger(AdAdvertismentServiceImpl.class);

	private static final String BASE_MESSAGE = "【AdAdvertismentServiceImpl服务类提供的基础操作增删改查等】";
	private static final String KEY_ADADVERTISMENT = "_key_advertisment";
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Autowired
	@Lazy(true)
	private AdAdvertismentMapper adAdvertismentMapper;
	
	@Autowired
	@Lazy(true)
	private AdAssetsMapper adAssetsMapper;

	@Override
	public Mapper<AdAdvertisment> getBaseMapper() {
		return adAdvertismentMapper;
	}

	public AdAdvertismentServiceImpl() {
		super();
		if (redisTemplate == null) {
			if (SpringContextHolder.getApplicationContext().containsBean(
					"redisTemplate")) {
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<AdAdvertisment> list) { // TODO
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<AdAdvertisment> advertisments = adAdvertismentMapper.selectByExample(example);
		
		List<String> assetsIds = new ArrayList<String>();
		
		for (AdAdvertisment advertisment : advertisments) {
			RedisUtil.removeRedisKey(redisTemplate, advertisment.getId()+KEY_ADADVERTISMENT);
			assetsIds.add(advertisment.getId());
		}
		
		AdAssetsCriteria adAssetsCriteria = new AdAssetsCriteria();
		adAssetsCriteria.createCriteria().andAdvertisementIdIn(assetsIds);
		
		List<AdAssets> adAssets = this.adAssetsMapper.selectByExample(adAssetsCriteria);
		if(adAssets.size()>0){
			for (AdAssets assets : adAssets) {
				if(assets.getPath()!=null){
					Path path = Paths.get(assets.getPath()); 
					try {
			            if(Files.exists(path)){
			            	Files.delete(path);
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
		
		// 逻辑操作
		int result = adAdvertismentMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

//	@CacheEvict(value = "AdAdvertisment", allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		AdAdvertismentCriteria adAdvertismentCriteria = new AdAdvertismentCriteria();
		adAdvertismentCriteria.createCriteria().andIdIn(list);
		List<AdAdvertisment> advertisments = adAdvertismentMapper.selectByExample(adAdvertismentCriteria);
		List<String> assetsIds = new ArrayList<String>();
		
		for (AdAdvertisment advertisment : advertisments) {
			RedisUtil.removeRedisKey(redisTemplate, advertisment.getId()+KEY_ADADVERTISMENT);
			assetsIds.add(advertisment.getId());
		}
		
		AdAssetsCriteria adAssetsCriteria = new AdAssetsCriteria();
		adAssetsCriteria.createCriteria().andAdvertisementIdIn(assetsIds);
		
		List<AdAssets> adAssets = this.adAssetsMapper.selectByExample(adAssetsCriteria);
		if(adAssets.size()>0){
			for (AdAssets assets : adAssets) {
				if(assets.getPath()!=null){
					Path path = Paths.get(assets.getPath()); 
					try {
			            if(Files.exists(path)){
			            	Files.delete(path);
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
		
		int result = adAdvertismentMapper.deleteByExample(adAdvertismentCriteria);

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
	@CacheEvict(value = "AdAdvertisment", key="#id+'_key_advertisment'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		AdAdvertisment adAdvertisment = this.selectByPrimaryKey(id);
		AdAssetsCriteria adAssetsCriteria = new AdAssetsCriteria();
		adAssetsCriteria.createCriteria().andAdvertisementIdEqualTo(adAdvertisment.getId());
		
		List<AdAssets> adAssets = this.adAssetsMapper.selectByExample(adAssetsCriteria);
		if(adAssets.size()>0){
			for (AdAssets assets : adAssets) {
				if(assets.getPath()!=null){
					Path path = Paths.get(assets.getPath()); 
					try {
			            if(Files.exists(path)){
			            	Files.delete(path);
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
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
//	@CachePut(value = "AdAdvertisment", key = "#record.id")
	public int insert(AdAdvertisment record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

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
//	@CachePut(value = "AdAdvertisment", key = "#record.id")
	public int insertSelective(AdAdvertisment record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
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
	public List<AdAdvertisment> selectByExample(Object example) {
		return adAdvertismentMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_advertisment'", value = "AdAdvertisment")
	@Transactional(readOnly = true)
	public AdAdvertisment selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		AdAdvertisment adAdvertisment = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return adAdvertisment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExampleSelective(AdAdvertisment record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		List<AdAdvertisment> assets = adAdvertismentMapper.selectByExample(example);
		for (AdAdvertisment assets2 : assets) {
			RedisUtil.removeRedisKey(redisTemplate, assets2.getId()+KEY_ADADVERTISMENT);
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
	public int updateByExample(AdAdvertisment record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		List<AdAdvertisment> assets = adAdvertismentMapper.selectByExample(example);
		for (AdAdvertisment assets2 : assets) {
			RedisUtil.removeRedisKey(redisTemplate, assets2.getId()+KEY_ADADVERTISMENT);
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
	@CacheEvict(value = "AdAdvertisment", key="#record.id+'_key_advertisment'")
	public int updateByPrimaryKeySelective(AdAdvertisment record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

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
	@CacheEvict(value = "AdAdvertisment", key="#record.id+'_key_advertisment'")
	public int updateByPrimaryKey(AdAdvertisment record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());

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
	public PageInfo<AdAdvertisment> selectByPageInfo(Object example, PageInfo<AdAdvertisment> pageInfo) {
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
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");// 此文件放在SRC目录下
		IAdAdvertismentService adAdvertismentServiceImpl = (IAdAdvertismentService) context.getBean("adAdvertismentServiceImpl");
		AdAdvertisment adAdvertisment = new AdAdvertisment();
		adAdvertisment.setId(System.currentTimeMillis() + "");
		int result = adAdvertismentServiceImpl.insertSelective(adAdvertisment);
		logger.info(result + "");
	}

}
