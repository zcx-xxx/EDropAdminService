package com.edrop.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edrop.pojo.Feedback;
import com.edrop.service.FeedbackService;
import com.edrop.service.UserOperationStatisticsService; 

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class TestFeedbackService {
	@Resource
	private FeedbackService feedbackServiceImpl;
	
	@Test
	public void getAllFeedbacks() {
		List<Feedback> list = feedbackServiceImpl.getAllFeedbacks(true);
		System.out.println(list.get(0).getUser().getUsername());
	}
	@Test
	public void testUpdateIsReaded() {
		feedbackServiceImpl.markReaded(1);
		return;
	}
	@Test
	public void testUpdateIsDeleted() {
		feedbackServiceImpl.markDeleted(1);
		return;
	}
	@Test
	public void testAddFeedback() {
//		feedbackServiceImpl.markDeleted(1);
		feedbackServiceImpl.addFeedback("what,fuck!!!", 1024);
		return;
	}
	
//	public void testA
}
