package com.edrop.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.edrop.service.ArticleService;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class HuffmanCodeTest {
	@Resource
	private ArticleService articleServiceImpl;
	
	@Test
	public void addArticleTest() {
		Integer id = articleServiceImpl.addArticleTest("# 张晨旭\r\n" + 
				"## 张晨旭\r\n" + 
				"- 你还呀\r\n" + 
				"- 我很好\r\n" + 
				"- 哦哦，那就好");
//		System.out.println(id);
	}
	
	@Test
	public void selectArticleTest() {
		String ans = articleServiceImpl.selectArticleById(7);
		System.out.println(ans);
	}
}
