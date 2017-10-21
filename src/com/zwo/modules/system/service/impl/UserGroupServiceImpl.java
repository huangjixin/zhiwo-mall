/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.system.dao.TbRoleMapper;
import com.zwo.modules.system.dao.TbUserGroupMapper;
import com.zwo.modules.system.dao.TbUserGroupRoleMapper;
import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.domain.TbUserGroupCriteria;
import com.zwo.modules.system.domain.TbUserGroupRoleCriteria;
import com.zwo.modules.system.service.ITbUserGroupService;
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
public class UserGroupServiceImpl extends BaseService<TbUserGroup> implements ITbUserGroupService {
	private static Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);

	private static final String BASE_MESSAGE = "銆怲bUserGroupServiceImpl鏈嶅姟绫绘彁渚涚殑鍩虹鎿嶄綔澧炲垹鏀规煡绛夈��";

	public static final String KEY_TBUSER_GROUP = "_key_tbUserGroup";
	
	@Autowired
	@Lazy(true)
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Autowired
	@Lazy(true)
	private TbUserGroupRoleMapper userGroupRoleMapper;
	
	@Autowired
	@Lazy(true)
	private TbUserGroupMapper tbUserGroupMapper;
	
	@Autowired
	@Lazy(true)
	private TbRoleMapper roleMapper;

	@Override
	public Mapper<TbUserGroup> getBaseMapper() {
		return tbUserGroupMapper;
	}


	public UserGroupServiceImpl() {
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
	 * @Override public int insertBatch(List<TbUserGroup> list) { // TODO
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
//	@CacheEvict(value = "TbUserGroup", allEntries = true)
	public int deleteByExample(Object example) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample鎵归噺鍒犻櫎寮�濮�");
		List<TbUserGroup> groups = this.selectByExample(example);
		for (TbUserGroup group : groups) {
			RedisUtil.removeRedisKey(redisTemplate, group.getId()+KEY_TBUSER_GROUP);
		}
		
		// 閫昏緫鎿嶄綔
		int result = tbUserGroupMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample鎵归噺鍒犻櫎缁撴潫");
		return result;
	}

	@CacheEvict(value = "TbUserGroup", allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch鎵归噺鍒犻櫎寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch鎵归噺鍒犻櫎ID涓猴細" + list.toString());

		// 閫昏緫鎿嶄綔
		TbUserGroupCriteria tbUserGroupCriteria = new TbUserGroupCriteria();
		tbUserGroupCriteria.createCriteria().andIdIn(list);
		int result = tbUserGroupMapper.deleteByExample(tbUserGroupCriteria);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch鎵归噺鍒犻櫎缁撴潫");
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
	@CacheEvict(value = "TbUserGroup", key="#id+'_key_tbUserGroup'")
	public int deleteByPrimaryKey(String id) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey鍒犻櫎寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey鍒犻櫎ID涓猴細" + id.toString());

		// 閫昏緫鎿嶄綔
		int result = super.deleteByPrimaryKey(id);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey鍒犻櫎缁撴潫");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insert(java.lang.Object)
	 */
	@Override
//	@CachePut(value = "TbUserGroup", key = "#record.id+'_key_tbUserGroup'")
	public int insert(TbUserGroup record) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆瀵硅薄涓猴細" + record.toString());

		// 濡傛灉鏁版嵁娌℃湁璁剧疆id,榛樿浣跨敤鏃堕棿鎴�
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insert(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆缁撴潫");
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
//	@Cacheable(value = "TbUserGroup", key = "#record.id+'_key_tbUserGroup'")
	public int insertSelective(TbUserGroup record) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆瀵硅薄涓猴細" + record.toString());

		// 濡傛灉鏁版嵁娌℃湁璁剧疆id,榛樿浣跨敤鏃堕棿鎴�
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert鎻掑叆缁撴潫");
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
	public List<TbUserGroup> selectByExample(Object example) {
		return super.selectByExample(example);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_tbUserGroup'", value = "TbUserGroup")
	@Transactional(readOnly = true)
	public TbUserGroup selectByPrimaryKey(String id) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ鍙傛暟涓猴細" + id);

		// 閫昏緫鎿嶄綔
		TbUserGroup tbUserGroup = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ缁撴潫");
		return tbUserGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExampleSelective(TbUserGroup record, Object example) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective鏇存柊寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective鏇存柊鏉′欢瀵硅薄涓猴細" + record.toString());
		List<TbUserGroup> groups = this.selectByExample(example);
		for (TbUserGroup group : groups) {
			RedisUtil.removeRedisKey(redisTemplate, group.getId()+KEY_TBUSER_GROUP);
		}
		
		// 閫昏緫鎿嶄綔
		int result = super.updateByExampleSelective(record, example);
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective鏇存柊缁撴潫");
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
	public int updateByExample(TbUserGroup record, Object example) {
		//鏃ュ織璁板綍
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample鏇存柊寮�濮�");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample鏇存柊瀵硅薄涓猴細" + record.toString());
		List<TbUserGroup> groups = this.selectByExample(example);
		for (TbUserGroup group : groups) {
			RedisUtil.removeRedisKey(redisTemplate, group.getId()+KEY_TBUSER_GROUP);
		}								
		//閫昏緫鎿嶄綔		
		int result = super.updateByExample(record, example);
		//鏃ュ織璁板綍
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample鏇存柊缁撴潫");
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
	@CacheEvict(value = "TbUserGroup", key="#record.id+'_key_tbUserGroup'")
	public int updateByPrimaryKeySelective(TbUserGroup record) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective鏇存柊寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective鏇存柊瀵硅薄涓猴細" + record.toString());
		
		// 閫昏緫鎿嶄綔
		int result = super.updateByPrimaryKeySelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective鏇存柊缁撴潫");
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
	@CacheEvict(value = "TbUserGroup", key="#record.id+'_key_tbUserGroup'")
	public int updateByPrimaryKey(TbUserGroup record) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey鏇存柊寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey鏇存柊瀵硅薄涓猴細" + record.toString());
		
		// 閫昏緫鎿嶄綔
		int result = super.updateByPrimaryKey(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey鏇存柊缁撴潫");
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
	public PageInfo<TbUserGroup> selectByPageInfo(Object example, PageInfo<TbUserGroup> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鍒嗛〉寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鍒嗛〉鍙傛暟锛�" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鍒嗛〉缁撴潫");
		return pageInfo;
	}

	@Override
	public List<TbRole> findByUserGroupId(String usergroupId) {
		return roleMapper.findByUserGroupId(usergroupId);
	}

	@Override
	public void batchConnectUserGroupRole(final List<String> roleIds, final String usergroupId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈鐢ㄦ埛缁勮鑹插紑濮�");
		String sql = " INSERT INTO tb_user_group_role (id,role_id,usergroup_id) VALUES (?,?,?)";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,UUID.randomUUID().toString().replaceAll("-", ""));
				ps.setString(1, System.currentTimeMillis() + "" + Math.round(Math.random() * 9999));
				ps.setString(2, roleIds.get(i));
				ps.setString(3, usergroupId);
			}
			@Override
			public int getBatchSize() {
				return roleIds.size();
			}
		});
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈鐢ㄦ埛缁勮鑹茬粨鏉�");
	}

	@Override
	public void batchUnconnectUserGroupRole(String usergroupId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈鐢ㄦ埛缁勮鑹插紑濮�");
		TbUserGroupRoleCriteria userGroupRoleCriteria = new TbUserGroupRoleCriteria(); 
		userGroupRoleCriteria.createCriteria().andUsergroupIdEqualTo(usergroupId);
		userGroupRoleMapper.deleteByExample(userGroupRoleCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈鐢ㄦ埛缁勮鑹茬粨鏉�");
	}

}
