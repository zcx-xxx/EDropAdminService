package com.edrop.mapper;

import java.util.List;

import com.edrop.pojo.UserPlaceOrderAddressInfo;

public interface UserPlaceOrderAddressInfoMapper {
	// 查询下单地址信息
	public List<UserPlaceOrderAddressInfo> selectOrderAddressInfo();
}
