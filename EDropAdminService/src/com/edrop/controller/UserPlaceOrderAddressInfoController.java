package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edrop.service.UserPlaceOrderAddressInfoService;

@Controller
@RequestMapping("/user_place_order_address_info")
public class UserPlaceOrderAddressInfoController {
	@Resource
	private UserPlaceOrderAddressInfoService userPlaceOrderAddressInfoServiceImpl;
	
	@ResponseBody
	@RequestMapping("get_order_address_info")
	public String getOrderAddressInfo() {
		String ans = userPlaceOrderAddressInfoServiceImpl.getOrderAddressInfo();
		return ans;
	}
}
