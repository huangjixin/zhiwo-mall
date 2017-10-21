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

import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbResourcesCriteria;
import com.zwo.modules.system.service.ITbResourcesService;
import com.zwo.modules.system.service.ITbResourcesService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/system-applicationContext.xml")
public class ResourcesServiceImplTest extends AbstractJUnit4SpringContextTests {
private static Logger logger = LoggerFactory.getLogger(ResourcesServiceImplTest.class);
	
    @Autowired
    private ITbResourcesService resourcesService;
    
    @Test
    public void insertSelectiveTest() {
    	TbResources resources = new TbResources();
    	resources.setAuthName("member:member:create");
    	resources.setName("会员新增");
    	resources.setCode("resourcesManageCreate");
    	resources.setParentId("150198229992573");
    	resources.setParentids("150183915978716");
    	resources.setType(TbResources.ResourceType.button);
    	
    	resourcesService.insertSelective(resources);
    	System.out.println(resources.getId());
    }
    
    
}
