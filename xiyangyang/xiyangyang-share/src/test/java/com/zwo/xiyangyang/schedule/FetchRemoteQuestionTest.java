/**
 * 
 */
package com.zwo.xiyangyang.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 黄记新
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchRemoteQuestionTest {
	@Autowired
	private FetchRemoteQuestions fetchRemoteQuestions;
	
	@Test
//	@Rollback(true) 
	public void testGet() {
		fetchRemoteQuestions.get("http://f.apiplus.net/gd11x5-2.json", "gd11x5", "广东11选5", "gd11x5");
		//fetchRemoteQuestions.get("http://f.apiplus.net/gd11x5-2.json","gd11x5","广东11选5","gd11x5");
	}
}
