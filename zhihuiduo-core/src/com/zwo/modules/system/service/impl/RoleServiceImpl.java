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
import com.zwo.modules.system.dao.TbResourcesMapper;
import com.zwo.modules.system.dao.TbRoleMapper;
import com.zwo.modules.system.dao.TbRoleResourcesMapper;
import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleCriteria;
import com.zwo.modules.system.domain.TbRoleResources;
import com.zwo.modules.system.domain.TbRoleResourcesCriteria;
import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.service.ITbRoleService;
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
public class RoleServiceImpl extends BaseService<TbRole> implements ITbRoleService {
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	private static final String BASE_MESSAGE = "銆怲bRoleServiceImpl鏈嶅姟绫绘彁渚涚殑鍩虹鎿嶄綔澧炲垹鏀规煡绛夈��";

	public static final String KEY_TBROLE = "_key_tbRole";
	
	@Autowired
	@Lazy(true)
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Autowired
	@Lazy(true)
	private TbRoleMapper roleMapper;
	
	@Autowired
	@Lazy(true)
	private TbResourcesMapper resourcesMapper;
	
	@Autowired
	@Lazy(true)
	private TbRoleResourcesMapper roleResourcesMapper;

	@Override
	public Mapper<TbRole> getBaseMapper() {
		return roleMapper;
	}

	public RoleServiceImpl() {
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
	 * @Override public int insertBatch(List<TbRole> list) { // TODO
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
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample鎵归噺鍒犻櫎寮�濮�");
		List<TbRole> roles = this.selectByExample(example);
		for (TbRole role : roles) {
			RedisUtil.removeRedisKey(redisTemplate, role.getId()+KEY_TBROLE);
		}
		// 閫昏緫鎿嶄綔
		int result = roleMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample鎵归噺鍒犻櫎缁撴潫");
		return result;
	}

	@Override
	public int deleteBatch(List<String> list) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch鎵归噺鍒犻櫎寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch鎵归噺鍒犻櫎ID涓猴細" + list.toString());

		// 閫昏緫鎿嶄綔
		TbRoleCriteria roleCriteria = new TbRoleCriteria();
		roleCriteria.createCriteria().andIdIn(list);
		List<TbRole> roles = this.selectByExample(roleCriteria);
		for (TbRole role : roles) {
			RedisUtil.removeRedisKey(redisTemplate, role.getId()+KEY_TBROLE);
		}
		
		int result = roleMapper.deleteByExample(roleCriteria);

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
	@CacheEvict(value = "TbRole", key="#id+'_key_tbRole'")
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
//	@CachePut(value = "TbRole", key = "#record.id+'_key_tbRole'")
	public int insert(TbRole record) {
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
//	@CachePut(value = "TbRole", key = "#record.id+'_key_tbRole'")
	public int insertSelective(TbRole record) {
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
	public List<TbRole> selectByExample(Object example) {
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
	@Cacheable(key = "#id+'_key_tbRole'", value = "TbRole")
	@Transactional(readOnly = true)
	public TbRole selectByPrimaryKey(String id) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ鍙傛暟涓猴細" + id);

		// 閫昏緫鎿嶄綔
		TbRole role = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey鏌ヨ缁撴潫");
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
//	@CacheEvict(value = "TbRole", allEntries = true)
	@Override
	public int updateByExampleSelective(TbRole record, Object example) {
		// 鏃ュ織璁板綍
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective鏇存柊寮�濮�");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective鏇存柊鏉′欢瀵硅薄涓猴細" + record.toString());
		List<TbRole> roles = this.selectByExample(example);
		for (TbRole role : roles) {
			RedisUtil.removeRedisKey(redisTemplate, role.getId()+KEY_TBROLE);
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
//	@CacheEvict(value = "TbRole", allEntries = true)
	public int updateByExample(TbRole record, Object example) {
		//鏃ュ織璁板綍
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample鏇存柊寮�濮�");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample鏇存柊瀵硅薄涓猴細" + record.toString());
		List<TbRole> roles = this.selectByExample(example);
		for (TbRole role : roles) {
			RedisUtil.removeRedisKey(redisTemplate, role.getId()+KEY_TBROLE);
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
	@CacheEvict(value = "TbRole", key="#record.id+'_key_tbRole'")
	public int updateByPrimaryKeySelective(TbRole record) {
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
	@CacheEvict(value = "TbRole", key="#record.id+'_key_tbRole'")
	public int updateByPrimaryKey(TbRole record) {
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
	public PageInfo<TbRole> selectByPageInfo(Object example, PageInfo<TbRole> pageInfo) {
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
	public void connectRoleResources(String resourcesId, String roleId) {
		TbRoleResources record = new TbRoleResources();
		record.setResourcesId(resourcesId);
		record.setRoleId(roleId);
		this.roleResourcesMapper.insert(record);
	}

	@Override
	public void unconnectRoleResources(String resourcesId, String roleId) {
		TbRoleResourcesCriteria example = new TbRoleResourcesCriteria();
		example.createCriteria().andResourcesIdEqualTo(resourcesId)
				.andRoleIdEqualTo(roleId);
		roleResourcesMapper.deleteByExample(example);
	}

	@Override
	public void batchConnectRoleResources(final List<TbRoleResources> roleResources, final String roleId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈瑙掕壊璧勬簮寮�濮�");
		for (TbRoleResources tbRoleResources : roleResources) {
			if(tbRoleResources.getId()==null){
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				tbRoleResources.setId(uuid);
			}
		}
		String sql = " INSERT INTO tb_role_resources (id,resources_id,role_id) VALUES (?,?,?)";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, roleResources.get(i).getId());
				ps.setString(2, roleResources.get(i).getResourcesId());
				ps.setString(3, roleId);
			}
			@Override
			public int getBatchSize() {
				return roleResources.size();
			}
		});
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺鍏宠仈瑙掕壊璧勬簮缁撴潫");
	}

	@Override
	public void batchUnconnectRoleResources(String roleId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺瑙ｉ櫎瑙掕壊璧勬簮寮�濮�");
		TbRoleResourcesCriteria roleResourcesCriteria = new TbRoleResourcesCriteria();
		roleResourcesCriteria.createCriteria().andRoleIdEqualTo(roleId);
		roleResourcesMapper.deleteByExample(roleResourcesCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鎵归噺瑙ｉ櫎瑙掕壊璧勬簮缁撴潫");
	}

	@Override
	@Transactional(readOnly = true)
	public List<TbResources> selectByRolename(String rolename) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鏍规嵁瑙掕壊鍚嶈繘琛屾煡璇㈣祫婧愬紑濮�");
		List<TbResources> list = resourcesMapper.selectByRolename(rolename);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鏍规嵁瑙掕壊鍚嶈繘琛屾煡璇㈣祫婧愮粨鏉�,绲愭灉鏉＄洰鏁颁负锛�"+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TbResources> selectByRoleId(String roleId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鏍规嵁瑙掕壊ID杩涜鏌ヨ璧勬簮寮�濮�");
		List<TbResources> list = resourcesMapper.selectByRoleId(roleId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "鏍规嵁瑙掕壊ID杩涜鏌ヨ璧勬簮缁撴潫,绲愭灉鏉＄洰鏁颁负锛�"+list.size());
		return list;
	}

}
