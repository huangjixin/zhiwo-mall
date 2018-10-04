/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.modules.core.mapper.BaseMapper;
import com.zwo.modules.core.service.impl.BaseServiceImpl;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.mapper.UserMapper;
import com.zwo.modules.system.service.IUserService;
import com.zwo.modules.system.utils.BPwdEncoderUtil;
import com.zwo.modules.system.vo.UserVo;

/**
 * @author 黄记新
 *
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String BASE_MESSAGE = "用户业务类UserServiceImpl增删改查";
	
	@Override
	protected BaseMapper<User> getBaseMapper() {
		return this.userMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	public Class getTypeClass() {
		return User.class;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public int insert(User record) {
		if(record.getPassword()!=null) {
			String pass = record.getPassword();
			pass = BPwdEncoderUtil.BCryptPassword(pass);
			record.setPassword(pass);
		}
		record.setAddTime(new Date());
		record.setEnabled(true);
		return super.insert(record);
	}
	

	@Override
	public int insertSelective(User record) {
		if(record.getPassword()!=null) {
			String pass = record.getPassword();
			pass = BPwdEncoderUtil.BCryptPassword(pass);
			record.setPassword(pass);
		}
		return super.insertSelective(record);
	}
	
	@Override
	public Set<String> findRoles(String username) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "查找角色开始，参数username的值是：" + username);
		}
		Set<String> result = new HashSet<>();
		List<String>list = this.userMapper.findRoles(username);
		if(list!=null && list.size()>0) {
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String roleName = (String) it.next();
				result.add(roleName);
			}
		}
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "查找角色結束,結果條目數為：" + result.size());

		return result;
	}

	@Override
	public Set<String> findPermissions(String username) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "查找权限开始，参数username的值是：" + username);
		}
		Set<String> result = new HashSet<>();
		List<String>list = this.userMapper.findPermissions(username);
		if(list!=null && list.size()>0) {
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String roleName = (String) it.next();
				result.add(roleName);
			}
		}
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "查找权限結束,結果條目數為：" + result.size());

		return result;
	}

	@Override
	public UserVo findByUsername(String username) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "查找用户开始，参数username的值是：" + username);
		}

		UserVo result = this.userMapper.findByUsername(username);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "查找用户結束,結果為：" + (result==null?"null": result.toString()+""));

		return result;
	}


}
