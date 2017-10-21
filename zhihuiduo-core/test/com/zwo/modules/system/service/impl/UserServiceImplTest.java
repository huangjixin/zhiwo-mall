/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import com.zwo.modules.system.domain.TbUserUserGroup;
import com.zwo.modules.system.service.ITbUserService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/system-applicationContext.xml")
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests {
private static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
    @Autowired
    private ITbUserService userService;
    
    @Test
    public void insertTest() {
    	TbUser user = new TbUser();
    	Date date = new Date();
    	user.setUsername("yangmiaoqing");
    	user.setRealName("杨苗青");
    	user.setPassword("ymq123");
    	user.setCreateDate(date);
    	user.setUpdateDate(date);
    	user.setDisable(true);
    	userService.insert(user);
        System.out.println(user.getId());
    }
    
    @Test
    public void insertSelectiveTest() {
    	TbUser user = new TbUser();
    	user.setUsername("yangmiaoqing");
    	user.setRealName("杨苗青3");
    	user.setPassword("ymq123");
    	user.setDisable(true);
    	userService.insertSelective(user);
    	System.out.println(user.getId());
    }
    
    @Test
    public void updateSelectiveTest() {
    	TbUser user = new TbUser();
    	user.setUsergroupId("150183679493710");
    	TbUserCriteria userCriteria = new TbUserCriteria();
    	userCriteria.createCriteria().andUsergroupIdIsNull();
    	userService.updateByExampleSelective(user, userCriteria);
    	System.out.println(user.getId());
    }
    

    @Test
    public void connectUserGroupRoleTest() {
    	String userGroupId = "150183679493710";
    	String roleId = "150183669467347";
    	userService.connectUserGroupRole(userGroupId, roleId);
    }
    
    @Test
    public void findPermissionsTest() {
    	Set<String> set = userService.findPermissions("13926205227");
    	for (String permission : set) {
			System.out.println(permission);
		}
    }
    
    @Test
    public void unconnectUserGroupRoleTest() {
    	String userGroupId = "150183679493710";
    	String roleId = "150183669467347";
    	userService.unconnectUserGroupRole(userGroupId, roleId);
    }
    
    @Test
    public void selectByExampleAndGroupNameTest() {
    	TbUserCriteria userCriteria = new TbUserCriteria();
    	userCriteria.createCriteria().andUsernameEqualTo("yangmiaoqing");
    	List<TbUserUserGroup> list = userService.selectByExampleAndGroupName(userCriteria, "adminGroup");
    	System.out.println(list.size());
    }
}
