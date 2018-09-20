package com.zwo.modules.system.service.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.SpringEurekaSystemApplication;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.service.IUserService;
 
/**
 * Created by asus1 on 2018/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringEurekaSystemApplication.class,IUserServiceTest.class})
public class IUserServiceTest {
    @Autowired
    private IUserService userService;
 
    @Test
    public void testSave() {
    	User record = new User();
    	int res = userService.insert(record);
    	assertEquals(1, res);
    }
}