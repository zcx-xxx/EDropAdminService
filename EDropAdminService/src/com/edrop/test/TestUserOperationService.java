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
import com.edrop.service.UserOperationStatisticsService; 

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class TestUserOperationService {
	@Resource
	private UserOperationStatisticsService userOperationStatisticsServiceImpl;
	
	@Test
	public void testSelectData() {
		String ans = userOperationStatisticsServiceImpl
				.getUserOperationData("user", "login");
		if (ans == null) {
			System.out.println("ans is null");
		} else {
			System.out.println(ans);
		}
	}
	
	/**
	 * 生成数据
	 * @Title: testAddNewData   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@Test
	public void testAddNewData() {
		Calendar start = Calendar.getInstance();
        start.set(2018, 0, 1);
        Long startTime = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.set(2021, 0, 0);
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();
        for (Long i = startTime; i <= endTime; i += oneDay) {
            Date date = new Date(i);
            Integer year = date.getYear() + 1900;
            Integer month = date.getMonth() + 1;
            Integer day = date.getDate();
            userOperationStatisticsServiceImpl.addOneNewOperation(year, month, day, "user", "login", random.nextInt(400) + 20);
        }
	}
}
