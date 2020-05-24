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
//		Integer id = articleServiceImpl.addArticle("","","1. extremely：非常、极其\r\n" + 
//				"   - extremely useful：非常有用的\r\n" + 
//				"2. extracting：提取\r\n" + 
//				"   - extracting information：提取信息\r\n" + 
//				"3. spreadsheet：电子表格\r\n" + 
//				"4. and while：然而、尽管\r\n" + 
//				"5. so that：以便\r\n" + 
//				"6. as ... as possible：尽可能 ...\r\n" + 
//				"   - as quickly as possible：尽可能快的\r\n" + 
//				"7. punctuation：标点\r\n" + 
//				"8. symbol：符号、标记\r\n" + 
//				"9. notation：符号\r\n" + 
//				"10. a couple lines：几行");
//		System.out.println(id);
	}
	
	@Test
	public void selectArticleTest() {
		String ans = articleServiceImpl.selectArticleById(2);
		System.out.println(ans);
	}
}
