package com.edrop.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import com.edrop.controller.FeedbackController;
import com.edrop.controller.UserController;
import com.sun.jersey.api.client.UniformInterfaceException;

@RunWith(SpringJUnit4ClassRunner.class) 
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springmvc.xml","classpath:applicationcontext.xml"})
public class TestFeedbackController extends AbstractTransactionalJUnit4SpringContextTests {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	@Before
	public void setUp() throws Exception {
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
	}
/*
(HttpServletRequest request, HttpServletResponse response,
			Integer id, String detailAddress, String phone, String qq, String username, 
			String password, MultipartFile imgFile, String address, String gender)
 * 
 */
	@Test
	public void testGetAllFeedbacks() throws UniformInterfaceException, IOException {
		String isReaded = "0";
		FeedbackController feedbackController = (FeedbackController) this.applicationContext.getBean("feedbackController");
		String ans = feedbackController.getAllFeedbackByIsReaded(isReaded);
		System.out.println(ans);
		return;
//		fail("Not yet implemented");
	}

}
