package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edrop.service.UserOperationStatisticsService;
import java.util.*;

@Controller
@RequestMapping("/user_operation_statistics")
public class UserOperationStatisticsController {
	@Resource
	private UserOperationStatisticsService userOperationStatisticsServiceImpl;
	
	@ResponseBody
	@RequestMapping("/get_user_dau_data")
	public String getDauData(
			@RequestParam(defaultValue = "user")String userOrEmployee,
			@RequestParam(defaultValue = "login")String loginOrRegister) {
		String ans = userOperationStatisticsServiceImpl
				.getUserOperationData(userOrEmployee, loginOrRegister);
		return ans;
	}
}
