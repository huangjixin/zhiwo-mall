/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.service.ITbUserGroupService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/system-applicationContext.xml")
public class UserGroupServiceImplTest extends AbstractJUnit4SpringContextTests {
private static Logger logger = LoggerFactory.getLogger(UserGroupServiceImplTest.class);
	
    @Autowired
    private ITbUserGroupService userGroupService;
    
    @Test
    public void insertTest() {
    	TbUserGroup user = new TbUserGroup();
    	
    	userGroupService.insert(user);
        System.out.println(user.getId());
    }
    
    @Test
    public void insertSelectiveTest() {
    	TbUserGroup role = new TbUserGroup();
    	role.setName("adminGroup");
    	userGroupService.insertSelective(role);
    	System.out.println(role.getId());
    }
}
