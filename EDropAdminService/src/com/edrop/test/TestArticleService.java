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
public class TestArticleService {
	@Resource
	private ArticleService articleServiceImpl;
	
	@Test
	public void selectArticleBriefInfo() {
		String ans = articleServiceImpl.getArticleBriefInfo(1, 1, 10);
		System.out.println(ans);
	}
	
	@Test
	public void likeOrCancelLikeTest() {
		articleServiceImpl.likeOrCancelLike(1, 1);
	}
	
	@Test
	public void selectContent() {
		String ans = articleServiceImpl.selectArticleById(6);
		System.out.println(ans);
	}
	
//	publi
}
