/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleResources;
import com.zwo.modules.system.service.ITbRoleService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/system-applicationContext.xml")
public class RoleServiceImplTest extends AbstractJUnit4SpringContextTests {
private static Logger logger = LoggerFactory.getLogger(RoleServiceImplTest.class);
	
    @Autowired
    private ITbRoleService roleService;
    
    
    @Test
    public void insertSelectiveTest() {
    	TbRole role = new TbRole();
    	role.setName("admin");
    	roleService.insertSelective(role);
    	System.out.println(role.getId());
    }
    
    @Test
    public void batchConnectTest() {
    	List<TbRoleResources>list = new ArrayList<TbRoleResources>();
    	TbRoleResources roleResources = new TbRoleResources();
    	roleResources.setResourcesId("150183915978716");
    	list.add(roleResources);
    	roleResources = new TbRoleResources();
    	roleResources.setResourcesId("15018393878145");
    	list.add(roleResources);
    	roleResources = new TbRoleResources();
    	roleResources.setResourcesId("150183945124351");
    	list.add(roleResources);
    	
    	roleService.batchConnectRoleResources(list, "150183669467347");
    }
    
}
