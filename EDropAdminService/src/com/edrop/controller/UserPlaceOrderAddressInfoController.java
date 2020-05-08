package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@ResponseBody
	@RequestMapping("get_order_address_info_by_page")
	public String getOrderAddressInfoByPage(
			@RequestParam(defaultValue = "0")String begin,
			@RequestParam(defaultValue = "1000")String count) {
		String ans = userPlaceOrderAddressInfoServiceImpl.getOrderAddressInfoByPage(
				Integer.valueOf(begin), Integer.valueOf(count));
		return ans;
	}
}
