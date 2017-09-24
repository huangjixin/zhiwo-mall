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
import com.zwo.modules.member.dao.GroupPurcseMemberMapper;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.domain.GroupPurcseMemberCriteria;
import com.zwo.modules.member.service.IGroupPurcseMemberService;
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
public class GroupPurcseMemberServiceImpl extends BaseService<GroupPurcseMember> implements IGroupPurcseMemberService {
	private static Logger logger = LoggerFactory.getLogger(GroupPurcseMemberServiceImpl.class);

	private static final String BASE_MESSAGE = "【GroupPurcseMemberServiceImpl服务类提供的基础操作增删改查等】";
	
	public static final String KEY_GROUP_PURCSE_MEMBER = "_key_GroupPurcseMember";
	
	public static final String KEY_GROUP_PURCSE_ID_MEMBERS = "_key_GroupPurcseIdMember";

	@Autowired
	@Lazy(true)
	private GroupPurcseMemberMapper groupPurcseMemberMapper;

	@Override
	public Mapper<GroupPurcseMember> getBaseMapper() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	public GroupPurcseMemberServiceImpl() {
		super();
		if (SpringContextHolder.getApplicationContext().containsBean(
				"redisTemplate")) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<GroupPurcseMember> list) { // TODO
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
		List<GroupPurcseMember> list = groupPurcseMemberMapper.selectByExample((GroupPurcseMemberCriteria)example);
		try {
			for (GroupPurcseMember GroupPurcseMember : list) {
				RedisUtil.removeRedisKey(redisTemplate, GroupPurcseMember.getId()+KEY_GROUP_PURCSE_MEMBER);
			}
		} catch (Exception e) {
			
		}
		
		// 逻辑操作
		int result = groupPurcseMemberMapper.deleteByExample((GroupPurcseMemberCriteria) example);

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
		GroupPurcseMemberCriteria GroupPurcseMemberCriteria = new GroupPurcseMemberCriteria();
		GroupPurcseMemberCriteria.createCriteria().andIdIn(list);
		List<GroupPurcseMember> GroupPurcseMembers = groupPurcseMemberMapper.selectByExample(GroupPurcseMemberCriteria);
		try {
			for (GroupPurcseMember GroupPurcseMember : GroupPurcseMembers) {
				RedisUtil.removeRedisKey(redisTemplate, GroupPurcseMember.getId()+KEY_GROUP_PURCSE_MEMBER);
			}
		} catch (Exception e) {
			
		}
		
		int result = groupPurcseMemberMapper.deleteByExample(GroupPurcseMemberCriteria);

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
	@CacheEvict(value = "GroupPurcseMember", key = "#id+''")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = groupPurcseMemberMapper.deleteByPrimaryKey(id);
		RedisUtil.removeRedisKey(redisTemplate, id+KEY_GROUP_PURCSE_MEMBER);
		
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
//	@Cacheable(value = "GroupPurcseMember", key = "#record.id")
	public int insert(GroupPurcseMember record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = groupPurcseMemberMapper.insert(record);
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
//	@CachePut(value = "GroupPurcseMember", key = "#record.id")
	public int insertSelective(GroupPurcseMember record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = groupPurcseMemberMapper.insertSelective(record);
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
	public List<GroupPurcseMember> selectByExample(Object example) {
		return groupPurcseMemberMapper.selectByExample((GroupPurcseMemberCriteria) example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_GroupPurcseMember'", value = "GroupPurcseMember")
	@Transactional(readOnly = true)
	public GroupPurcseMember selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		GroupPurcseMember GroupPurcseMember = groupPurcseMemberMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return GroupPurcseMember;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExampleSelective(GroupPurcseMember record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		List<GroupPurcseMember> list = groupPurcseMemberMapper.selectByExample((GroupPurcseMemberCriteria)example);
		for (GroupPurcseMember GroupPurcseMember : list) {
			RedisUtil.removeRedisKey(redisTemplate, GroupPurcseMember.getId()+KEY_GROUP_PURCSE_MEMBER);
		}
		// 逻辑操作
		int result = groupPurcseMemberMapper.updateByExampleSelective(record, (GroupPurcseMemberCriteria) example);
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
	public int updateByExample(GroupPurcseMember record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());
		List<GroupPurcseMember> list = groupPurcseMemberMapper.selectByExample((GroupPurcseMemberCriteria)example);
		for (GroupPurcseMember GroupPurcseMember : list) {
			RedisUtil.removeRedisKey(redisTemplate, GroupPurcseMember.getId()+KEY_GROUP_PURCSE_MEMBER);
		}
		// 逻辑操作
		int result = groupPurcseMemberMapper.updateByExample(record, (GroupPurcseMemberCriteria) example);
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
	@CacheEvict(value = "GroupPurcseMember", key = "#record.id+'_key_GroupPurcseMember'")
	public int updateByPrimaryKeySelective(GroupPurcseMember record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		RedisUtil.removeRedisKey(redisTemplate, record.getId()+KEY_GROUP_PURCSE_MEMBER);
		
		// 逻辑操作
		int result = groupPurcseMemberMapper.updateByPrimaryKeySelective(record);
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
	@CacheEvict(value = "GroupPurcseMember", key = "#record.id+'_key_GroupPurcseMember'")
	public int updateByPrimaryKey(GroupPurcseMember record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		RedisUtil.removeRedisKey(redisTemplate, record.getId()+KEY_GROUP_PURCSE_MEMBER);
		// 逻辑操作
		int result = groupPurcseMemberMapper.updateByPrimaryKey(record);
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
	public PageInfo<GroupPurcseMember> selectByPageInfo(Object example, PageInfo<GroupPurcseMember> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<GroupPurcseMember> list = groupPurcseMemberMapper.selectByExample((GroupPurcseMemberCriteria) example);
		PageInfo<GroupPurcseMember> page = new PageInfo<GroupPurcseMember>(list);
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
	public int countByGroupPurcseId(String groupPurcseId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据开团的ID查询中间表个数开始，团的参数是："+groupPurcseId);
		GroupPurcseMemberCriteria criteria = new GroupPurcseMemberCriteria();
		criteria.createCriteria().andGroupPurcseIdEqualTo(groupPurcseId);
		int result = groupPurcseMemberMapper.countByExample(criteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据开团的ID查询中间表个数结束，结果个数是："+result);
		return result;
	}

	@Override
	public List<GroupPurcseMember> selectByGroupPurcseId(String groupPurcseId) {
		GroupPurcseMemberCriteria criteria = new GroupPurcseMemberCriteria();
		criteria.createCriteria().andGroupPurcseIdEqualTo(groupPurcseId);
		List<GroupPurcseMember> list = groupPurcseMemberMapper.selectByExample(criteria);
		return list;
	}

}
