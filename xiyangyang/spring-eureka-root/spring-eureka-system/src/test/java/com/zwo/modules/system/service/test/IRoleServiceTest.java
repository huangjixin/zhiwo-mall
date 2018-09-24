package com.zwo.modules.system.service.test;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.SpringEurekaSystemApplication;
import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.service.IRoleService;
 
/**
 * Created by asus1 on 2018/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringEurekaSystemApplication.class,IRoleServiceTest.class})
public class IRoleServiceTest {
    @Autowired
    private IRoleService roleService;
 
    @Test
    public void testSave() {
    	Role record = new Role();
    	record.setName("admin_role");
    	record.setAddTime(new Date());
    	int res = roleService.insert(record);
    	assertEquals(1, res);
    }
}