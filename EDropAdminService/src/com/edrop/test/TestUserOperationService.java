package com.edrop.test;

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
		List<Integer> ans = userOperationStatisticsServiceImpl
				.getUserOperationDataByYear(2020, "user", "login");
		if (ans == null) {
			System.out.println("ans is null");
		} else {
			for (int i = 1; i < ans.size(); ++i) {
				System.out.print(ans.get(i) + ", ");
			}
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
		Integer[] years = {2018, 2019, 2020};
		Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
		String[] userOrEmployee = {"user", "employee"};
		String[] loginOrRegister = {"login", "register"};
		
		Random random = new Random();
		System.out.println("test");
//		for (int i = 0; i < 6000; ++i) {
//			userOperationStatisticsServiceImpl.addOneNewOperation(years[random.nextInt(years.length)],
//					months[random.nextInt(months.length)], days[random.nextInt(days.length)],
//					userOrEmployee[random.nextInt(userOrEmployee.length)], 
//					loginOrRegister[random.nextInt(loginOrRegister.length)]);
//		}
	}
}
