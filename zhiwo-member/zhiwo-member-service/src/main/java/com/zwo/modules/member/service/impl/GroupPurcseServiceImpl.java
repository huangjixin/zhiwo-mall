/**
 * 
 */
package com.zwo.modules.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.dao.GroupPurcseMapper;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseCriteria;
import com.zwo.modules.member.service.IGroupPurcseService;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GroupPurcseServiceImpl extends BaseService<GroupPurcse> implements
		IGroupPurcseService {
	private static Logger logger = LoggerFactory
			.getLogger(GroupPurcseServiceImpl.class);

	private static final String BASE_MESSAGE = "【GroupPurcseServiceImpl服务类提供的基础操作增删改查等】";

	private static final String KEY_GROUP_PURCSE = "_key_GroupPurcse";

	@Autowired
	@Lazy(true)
	private GroupPurcseMapper groupPurcseMapper;

	@Override
	public Mapper<GroupPurcse> getBaseMapper() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<GroupPurcse> list) { // TODO
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

	public GroupPurcseServiceImpl() {
		super();
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}

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
		List<GroupPurcse> list = groupPurcseMapper
				.selectByExample((GroupPurcseCriteria) example);
		try {
			for (GroupPurcse groupPurcse : list) {
				RedisUtil.removeRedisKey(redisTemplate, groupPurcse.getId()
						+ KEY_GROUP_PURCSE);
			}
		} catch (Exception e) {

		}

		// 逻辑操作
		int result = groupPurcseMapper
				.deleteByExample((GroupPurcseCriteria) example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		GroupPurcseCriteria GroupPurcseCriteria = new GroupPurcseCriteria();
		GroupPurcseCriteria.createCriteria().andIdIn(list);
		List<GroupPurcse> groupPurcses = groupPurcseMapper
				.selectByExample(GroupPurcseCriteria);
		try {
			for (GroupPurcse groupPurcse : groupPurcses) {
				RedisUtil.removeRedisKey(redisTemplate, groupPurcse.getId()
						+ KEY_GROUP_PURCSE);
			}
		} catch (Exception e) {

		}

		int result = groupPurcseMapper.deleteByExample(GroupPurcseCriteria);

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
	@CacheEvict(value = "GroupPurcse", key = "#id+''")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为："
					+ id.toString());

		// 逻辑操作
		int result = groupPurcseMapper.deleteByPrimaryKey(id);
		RedisUtil.removeRedisKey(redisTemplate, id + KEY_GROUP_PURCSE);

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
	// @Cacheable(value = "GroupPurcse", key = "#record.id")
	public int insert(GroupPurcse record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = groupPurcseMapper.insert(record);
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
	// @CachePut(value = "GroupPurcse", key = "#record.id")
	public int insertSelective(GroupPurcse record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = groupPurcseMapper.insertSelective(record);
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
	public List<GroupPurcse> selectByExample(Object example) {
		return groupPurcseMapper.selectByExample((GroupPurcseCriteria) example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_GroupPurcse'", value = "GroupPurcse")
	@Transactional(readOnly = true)
	public GroupPurcse selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		GroupPurcse GroupPurcse = groupPurcseMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return GroupPurcse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExampleSelective(GroupPurcse record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为："
					+ record.toString());
		List<GroupPurcse> list = groupPurcseMapper
				.selectByExample((GroupPurcseCriteria) example);
		for (GroupPurcse groupPurcse : list) {
			RedisUtil.removeRedisKey(redisTemplate, groupPurcse.getId()
					+ KEY_GROUP_PURCSE);
		}
		// 逻辑操作
		int result = groupPurcseMapper.updateByExampleSelective(record,
				(GroupPurcseCriteria) example);
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
	public int updateByExample(GroupPurcse record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为："
					+ record.toString());
		List<GroupPurcse> list = groupPurcseMapper
				.selectByExample((GroupPurcseCriteria) example);
		for (GroupPurcse groupPurcse : list) {
			RedisUtil.removeRedisKey(redisTemplate, groupPurcse.getId()
					+ KEY_GROUP_PURCSE);
		}
		// 逻辑操作
		int result = groupPurcseMapper.updateByExample(record,
				(GroupPurcseCriteria) example);
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
	@CacheEvict(value = "GroupPurcse", key = "#record.id+'_key_GroupPurcse'")
	public int updateByPrimaryKeySelective(GroupPurcse record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为："
					+ record.toString());
		RedisUtil.removeRedisKey(redisTemplate, record.getId()
				+ KEY_GROUP_PURCSE);

		// 逻辑操作
		int result = groupPurcseMapper.updateByPrimaryKeySelective(record);
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
	@CacheEvict(value = "GroupPurcse", key = "#record.id+'_key_GroupPurcse'")
	public int updateByPrimaryKey(GroupPurcse record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为："
					+ record.toString());
		RedisUtil.removeRedisKey(redisTemplate, record.getId()
				+ KEY_GROUP_PURCSE);
		// 逻辑操作
		int result = groupPurcseMapper.updateByPrimaryKey(record);
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
	public PageInfo<GroupPurcse> selectByPageInfo(Object example,
			PageInfo<GroupPurcse> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<GroupPurcse> list = groupPurcseMapper
				.selectByExample((GroupPurcseCriteria) example);
		PageInfo<GroupPurcse> page = new PageInfo<GroupPurcse>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	
	@Override
	@Transactional(readOnly = true)
	@Cacheable(key = "#productId+'_key_GroupPurcse'", value = "GroupPurcses")
	public List<GroupPurcse> selectGroupPurcseByPId(String productId,boolean disable) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据产品ID查询没有成团的列表，仅查前面十条记录开始，传入的商品ID是"+productId);
		List<GroupPurcse> list = groupPurcseMapper.selectGroupPurcseByPId(productId,disable);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据产品ID查询没有成团的列表，仅查前面十条记录结束，结果数目："+list.size());
		return list;
	}
}
