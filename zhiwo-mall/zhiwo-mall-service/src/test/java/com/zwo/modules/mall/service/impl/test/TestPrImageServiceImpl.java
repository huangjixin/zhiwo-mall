package com.zwo.modules.mall.service.impl.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.service.IPrImageService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/mall-applicationContext.xml")
public class TestPrImageServiceImpl extends TestCase {

	@Autowired
    private IPrImageService prImageService;
	
	public void testDeleteByExample() {
		fail("Not yet implemented");
	}

	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	public void testInsert() {
		fail("Not yet implemented");
	}

	public void testInsertSelective() {
		PrImage prImage = new PrImage();
		prImage.setId(System.currentTimeMillis()+"");
		int result = prImageService.insertSelective(prImage);
		assertEquals(1, result);
	}

	public void testSelectByExample() {
		fail("Not yet implemented");
	}

	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	public void testSelectByPageInfo() {
		fail("Not yet implemented");
	}

}
