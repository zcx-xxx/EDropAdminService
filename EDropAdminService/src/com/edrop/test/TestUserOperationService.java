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
//        start.set(2018, 0, 0);
        start.set(2018, 0, 0);
        Long startTime = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
//        end.set(2018, 3, 0);
        end.set(2021, 0, 0);
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();
        int idx = 1;
        for (Long i = startTime; i <= endTime; i += oneDay) {
            Date date = new Date(i);
            Integer year = date.getYear() + 1900;
            Integer month = date.getMonth() + 1;
            Integer day = date.getDate();
            if (idx < 90) {
	            userOperationStatisticsServiceImpl.addOneNewOperation(year, month, day, "user", "login", 
	            		(int)(2 * idx + random.nextInt(10) * (int)Math.pow(-1, random.nextInt(10) + 2)) % (3 * idx++));            	
            } else {
            	userOperationStatisticsServiceImpl.addOneNewOperation(year, month, day, "user", "login", 
            			random.nextInt(50) + 150 + (idx % 5) * random.nextInt(15) + random.nextInt(15) * (int)Math.pow(-1, random.nextInt(10) + 2));            	
            }
        }
	}
}
