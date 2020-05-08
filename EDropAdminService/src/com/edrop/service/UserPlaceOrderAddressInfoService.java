package com.edrop.service;

import java.util.List;

import com.edrop.pojo.UserPlaceOrderAddressInfo;

public interface UserPlaceOrderAddressInfoService {
	// 获取订单地址信息
	public String getOrderAddressInfo();
	
	// 多线程查询数据
	public String getOrderAddressInfoByMultipleThread();
	
	// 分页查询
	public String getOrderAddressInfoByPage(Integer begin, Integer count);
}
