package com.zwo.modules.system.service.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.SpringEurekaSystemApplication;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.domain.UserCriteria;
import com.zwo.modules.system.service.IUserService;

/**
 * Created by asus1 on 2018/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { SpringEurekaSystemApplication.class, IUserServiceTest.class })
public class IUserServiceTest {
	@Autowired
	private IUserService userService;

	@Test
	public void testSave() {
		User record = new User();
		record.setUsername("huangjixin");
		record.setPassword("123456");
		int res = userService.insert(record);
		assertEquals(1, res);
	}

	@Test
	public void testSelectByExample() {
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.createCriteria().andUsernameEqualTo("1");
		List<User> list = userService.selectByExample(userCriteria);
		for (User user : list) {
			System.out.println(user.toString());
		}

		assertEquals(1, list.size());
	}
}