/**
 * 
 */
package com.zwo.xiyangyang.modules.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.shiro.PasswordHelper;
import com.zwo.xiyangyang.modules.sys.dao.ResourcesMapper;
import com.zwo.xiyangyang.modules.sys.dao.RoleMapper;
import com.zwo.xiyangyang.modules.sys.dao.UserMapper;
import com.zwo.xiyangyang.modules.sys.domain.Role;
import com.zwo.xiyangyang.modules.sys.domain.User;
import com.zwo.xiyangyang.modules.sys.service.IUserService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	private static final String MESSAGE="系统用户基础操作";
	
	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Mapper<User> getBaseMapper() {
		return userMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return UserServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return User.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统用户基础操作";
	}

	/**
	 * 插入一条记录
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int insert(User record) {
		if(!StringUtils.isEmpty(record.getPassword())) {
			String password = PasswordHelper.encryptPassword(record.getPassword());
			record.setPassword(password);
		}
		return super.insert(record);
	}

	/**
	 * 有选择地进行插入记录
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int insertSelective(User record) {
		if(!StringUtils.isEmpty(record.getPassword())) {
			String password = PasswordHelper.encryptPassword(record.getPassword());
			record.setPassword(password);
		}
		return super.insertSelective(record);
	}

	/**
	 * 根据条件有选择的更新。
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int updateByPrimaryKeySelective(User record) {
		if(!StringUtils.isEmpty(record.getPassword())) {
			String password = PasswordHelper.encryptPassword(record.getPassword());
			record.setPassword(password);
		}
		return super.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据ID进行记录更新。
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public int updateById(User record) {
		if(!StringUtils.isEmpty(record.getPassword())) {
			String password = PasswordHelper.encryptPassword(record.getPassword());
			record.setPassword(password);
		}
		return super.updateById(record);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Set<String> findPermissions(String username) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询权限开始，参数username是："+username);
				
		List<String> list = resourcesMapper.selectResoucesByUsername(username);
		Set<String> set = new HashSet<String>();
		for (String name : list) {
			set.add(name);
		}
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询权限结束，结果："+list.size());
		return set;
	}

	@Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询用户开始，参数username是："+username);
		Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        
        Example.Criteria mobileCriteria = example.createCriteria();
        criteria.andEqualTo("mobilPhone", username);
        
        Example.Criteria emailCriteria = example.createCriteria();
        criteria.andEqualTo("email", username);
        
        example.or(mobileCriteria);
        example.or(emailCriteria);
		List<User> list = userMapper.selectByExample(example);
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询用户结束，结果："+list.size());
		return list.size()>0?list.get(0):null;
	}

	@Transactional(readOnly = true)
	@Override
	public Set<String> findRoles(String username) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据角色名查询权限开始，参数username是："+username);
				
		List<Role> list = roleMapper.selectRoleByUsername(username);
		Set<String> set = new HashSet<String>();
		for (Role role : list) {
			set.add(role.getName());
		}
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据角色名查询权限结束，结果："+list.size());
		return set;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User selectByPrimaryKey(String id) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "查询单条记录开始，参数id的值是：" + id);
		}

		User result = this.userMapper.selectById(id);
		if (logger.isInfoEnabled()) {
			try {
				String jsonStr = null;
				if (result != null) {
					Gson gson = new Gson();
					jsonStr = gson.toJson((Object) result);
				} else {
					jsonStr = "查询不到";
				}

				logger.info(getBaseMessage() + "查询单条记录结果：" + jsonStr);
			} catch (Exception e) {
				logger.info(getBaseMessage() + "查询单条记录结果在转换成对象打印日志的时候发生了异常，不影响查询结果");
			}
			
		}

		return result;
	}

}
