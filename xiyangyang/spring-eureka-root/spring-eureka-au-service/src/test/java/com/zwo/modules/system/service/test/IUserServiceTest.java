package com.zwo.modules.system.service.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.SpringEurekaAuServiceApplication;

/**
 * Created by asus1 on 2018/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { SpringEurekaAuServiceApplication.class, IUserServiceTest.class })
public class IUserServiceTest {
	public static void main(String[] args) {
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjMxNzkwNTU3NjksInVzZXJfbmFtZSI6IjEiLCJhdXRob3JpdGllcyI6WyJzeXN0ZW06dXNlciIsInN5c3RlbTpyZXNvdXJjZTplZGl0Iiwic3lzdGVtOnVzZXI6dmlldyIsInN5c3RlbTpyZXNvdXJjZSIsInN5c3RlbTpyZXNvdXJjZTp2aWV3Iiwic3lzdGVtOnVzZXI6ZGVsZXRlIiwic3lzdGVtOnJlc291cmNlOmRlbGV0ZSIsInN5c3RlbTp1c2VyOmVkaXQiXSwianRpIjoiYTc1NTNkM2YtNTcwNC00N2VjLWEzNDQtMzQwNmViMmM2Njg1IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.CPgSibLCsSw4_i-pM7xbctuWIWJTPFYsu2e5c3x72-1aOPKgJH3P-jpHSwq_oIKlW88K1LNeb_q4701-FW1ghbJPvzCFtNEO5jiyDoeuh4xrwsMALyY0yC8bEZxF6q8zMwmM1eeJV9-w3VyZNUr_Xcy-Ju_WZGVkpy2qdHBBaqyquRFcAHbLHr2zpprh_TSNyqPYKD_ogYQy_XMk0MEWPFeHLTNwxb-Z9uPwXnPVFObsr-EgZq7sNadtuo_bOaOWreD3qp7sZDcETzfokae0yoBFdMeT7Gj9OVzpAt8JuPxD5TYYxg9sXEbMtlj51rW-AzwY4UzXDs8b51u66X4tYw";
		Jwt jwt = JwtHelper.decode(token);
		System.out.println(jwt.getClaims());
	}
}