package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edrop.service.UserOperationStatisticsService;
import java.util.*;

@Controller
@RequestMapping("/user_operation_statistics")
public class UserOperationStatisticsController {
	@Resource
	private UserOperationStatisticsService userOperationStatisticsServiceImpl;
	
	@RequestMapping("/year")
	public String getUserOperationByYear(
			@RequestParam(defaultValue = "-1")String year, 
			@RequestParam(defaultValue = "user")String userOrEmployee, 
			@RequestParam(defaultValue = "login")String loginOrRegister) {
		// 没有指定年限，默认为当前系统的年限
		if (Integer.valueOf(year) == -1) {
			Calendar date = Calendar.getInstance();
	        year = String.valueOf(date.get(Calendar.YEAR));
		}
		List<Integer> ans = userOperationStatisticsServiceImpl
				.getUserOperationDataByYear(Integer.valueOf(year), userOrEmployee, loginOrRegister);
		return "";
	}
}
