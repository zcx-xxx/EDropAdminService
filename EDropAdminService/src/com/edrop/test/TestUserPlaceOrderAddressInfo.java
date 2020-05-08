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

import com.edrop.pojo.UserPlaceOrderAddressInfo;
import com.edrop.service.UserOperationStatisticsService;
import com.edrop.service.UserPlaceOrderAddressInfoService; 

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class TestUserPlaceOrderAddressInfo {
	@Resource
	private UserPlaceOrderAddressInfoService userPlaceOrderAddressInfoServiceImpl;
	
	@Test
	public void testSelectAllData() {
		Long beginTimes = System.currentTimeMillis();
		String ans = userPlaceOrderAddressInfoServiceImpl.getOrderAddressInfo();
		Long endTimes = System.currentTimeMillis();
//		System.out.println(ans);
		System.out.println("without thread cost times:" + ((endTimes - beginTimes) / 1000));
	}
	
	@Test
	public void testMultipleThreadSelect() {
		Long beginTimes = System.currentTimeMillis();
		String ans = userPlaceOrderAddressInfoServiceImpl.getOrderAddressInfoByMultipleThread();
		Long endTimes = System.currentTimeMillis();
//		System.out.println(ans);
		System.out.println("use thread cost times:" + ((endTimes - beginTimes) / 1000));
	}
}
