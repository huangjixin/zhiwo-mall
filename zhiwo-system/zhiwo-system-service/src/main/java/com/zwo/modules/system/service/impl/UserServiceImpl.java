/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.system.dao.TbResourcesMapper;
import com.zwo.modules.system.dao.TbRoleMapper;
import com.zwo.modules.system.dao.TbUserGroupRoleMapper;
import com.zwo.modules.system.dao.TbUserMapper;
import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import com.zwo.modules.system.domain.TbUserGroupRole;
import com.zwo.modules.system.domain.TbUserGroupRoleCriteria;
import com.zwo.modules.system.domain.TbUserUserGroup;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.utils.PasswordHelper;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseService<TbUser> implements ITbUserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final String BASE_MESSAGE = "【TbUserServiceImpl服务类提供的基础操作增删改查等】";

	@Autowired
	@Lazy(true)
	private TbUserMapper tbUserMapper;

	@Autowired
	@Lazy(true)
	private TbResourcesMapper resourcesMapper;

	@Autowired
	@Lazy(true)
	private TbRoleMapper roleMapper;

	@Autowired
	@Lazy(true)
	private TbUserGroupRoleMapper userGroupRoleMapper;

	@Override
	public Mapper<TbUser> getBaseMapper() {
		return tbUserMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<TbUser> list) { // TODO
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
	@CacheEvict(value = "TbUser", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = tbUserMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "TbUser", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		TbUserCriteria tbUserCriteria = new TbUserCriteria();
		tbUserCriteria.createCriteria().andIdIn(list);
		int result = tbUserMapper.deleteByExample(tbUserCriteria);

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
	@CacheEvict(value = "TbUser", key = "#id+'_user'")
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
//	@CachePut(value = "TbUser", key = "#record.id+'_user'")
	public int insert(TbUser record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		// 逻辑操作
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
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
//	@CachePut(value = "TbUser", key = "#record.id+'_user'")
	public int insertSelective(TbUser record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		if (record.getPassword() != null && !"".equals(record.getPassword())) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
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
	public List<TbUser> selectByExample(Object example) {
		return tbUserMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_user'", value = "TbUser")
	@Transactional(readOnly = true)
	public TbUser selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		TbUser tbUser = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return tbUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "TbUser", allEntries = true)
	@Override
	public int updateByExampleSelective(TbUser record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());

		// 逻辑操作
		if (record.getPassword() != null && !"".equals(record.getPassword())) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
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
	@CacheEvict(value = "TbUser", allEntries = true)
	public int updateByExample(TbUser record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());

		// 逻辑操作
		if (record.getPassword() != null && !"".equals(record.getPassword())) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
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
	@CacheEvict(value = "TbUser", key = "#record.id+'_user'")
	public int updateByPrimaryKeySelective(TbUser record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

		// 逻辑操作
		if (record.getPassword() != null && !"".equals(record.getPassword())) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
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
	@CacheEvict(value = "TbUser", key = "#record.id+'_user'")
	public int updateByPrimaryKey(TbUser record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());

		// 逻辑操作
		if (record.getPassword() != null && !"".equals(record.getPassword())) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}

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
//	@Cacheable(value = "TbUsersPageInfoCache", key = "#root.target+'_'+#root.method.name+'_'+#pageInfo.startRow+'_'+#pageInfo.endRow", condition = "#example==null")
	@Transactional(readOnly = true)
	@Override
	public PageInfo<TbUser> selectByPageInfo(Object example, PageInfo<TbUser> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}


	@Override
	public TbUser findByUsername(String username) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findByUsername查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findByUsername查询参数为：" + username);

		TbUserCriteria example = new TbUserCriteria();
		example.createCriteria().andUsernameEqualTo(username);
		TbUserCriteria.Criteria criteria = example.createCriteria().andMobilPhoneEqualTo(username);
		example.or(criteria);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findByUsername查询结束，结果个数为：" + list.size());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Set<String> findRoles(String username) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findRoles查询开始");
		List<TbRole> list = roleMapper.selectByUsernameOrPhone(username);
		Set<String> set = new HashSet<String>(list.size());
		for (TbRole role : list) {
			set.add(role.getName());
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findRoles查询结束");
		if (logger.isInfoEnabled())
			logger.info("角色集合是：{：" + set.toString() + "}");
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findPermissions查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "findPermissions查询参数为：" + username);
		List<TbResources> resources = resourcesMapper.selectByUsernameOrMPhone(username);
		Set<String> set = new HashSet<String>(resources.size());
		for (TbResources resource : resources) {
			if (null != resource.getAuthName() && !"".equals(resource.getAuthName())) {
				set.add(resource.getAuthName());
			}
		}
		return set;
	}

	@Override
	public TbUser login(TbUser user) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "login查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "login查询参数为：" + user.toString());

		TbUserCriteria example = new TbUserCriteria();
		TbUserCriteria.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		TbUserCriteria.Criteria criteria1 = example.createCriteria();
		criteria1.andUsernameEqualTo(user.getMobilPhone()).andPasswordEqualTo(user.getPassword());
		TbUserCriteria.Criteria criteria2 = example.createCriteria();
		criteria2.andEmailEqualTo(user.getMobilPhone()).andPasswordEqualTo(user.getPassword());
		example.or(criteria1);
		example.or(criteria2);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "login查询结束，结果个数为：" + list.size());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void connectUserGroupRole(String userGroupId, String roleId) {
		TbUserGroupRole record = new TbUserGroupRole();
		record.setId(new Date().getTime() + "" + Math.round(Math.random() * 99));
		record.setUsergroupId(userGroupId);
		record.setRoleId(roleId);
		userGroupRoleMapper.insert(record);
	}

	@Override
	public void unconnectUserGroupRole(String userGroupId, String roleId) {
		TbUserGroupRoleCriteria example = new TbUserGroupRoleCriteria();
		example.createCriteria().andUsergroupIdEqualTo(userGroupId).andRoleIdEqualTo(roleId);
		userGroupRoleMapper.deleteByExample(example);
	}

	@Override
	public List<TbUserUserGroup> selectByExampleAndGroupName(TbUserCriteria example, String groupName) {
		List<TbUserUserGroup> list = null;
//				tbUserMapper.selectByExampleAndGroupCode(example, groupName);
		return list;
	}

}
