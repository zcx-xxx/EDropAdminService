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
		Integer id = articleServiceImpl.addArticleTest("The scrcpy client necessarily connects to the device via adb. In particular, it executes the scrcpy server via adb shell to get shell permissions (to capture the video without asking and to inject events).\r\n" + 
				"\r\n" + 
				"If from your shell you can see your device via adb devices, then you could use scrcpy. Maybe you could adb connect to localhost. You could check #100 (comment), a project which adb-connects to an Android device from another Android device. It should work to connect to localhost too in theory.\r\n" + 
				"");
//		System.out.println(id);
	}
	
	@Test
	public void selectArticleTest() {
		String ans = articleServiceImpl.selectArticleById(14);
		System.out.println(ans);
	}
}
