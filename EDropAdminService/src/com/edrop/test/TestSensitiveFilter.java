package com.edrop.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edrop.utils.ACFind;

//import com.edrop.utils.SensitiveFilterUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class TestSensitiveFilter {
	@Resource
	private ACFind aCFind;
	@Test
	public void testSensitiveFilter() {
		String text = "hahha性王八蛋王八蛋蛋lalal张晨旭";
		String ans = aCFind.sensitiveFilter(text);
		System.out.println(ans);
//		String ans = SensitiveFilterUtils.sensitiveWordsFilter(text);
//		System.out.println(ans);
	}
}
