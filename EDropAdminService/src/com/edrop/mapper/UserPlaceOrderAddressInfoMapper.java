package com.edrop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.UserPlaceOrderAddressInfo;

public interface UserPlaceOrderAddressInfoMapper {
	// 查询下单地址信息
	public List<UserPlaceOrderAddressInfo> selectOrderAddressInfo();
	
	// 分页查询
	public List<UserPlaceOrderAddressInfo> selectOrderAddressInfoByPage(
			@Param("begin")Integer begin, @Param("count")Integer count);

	// 获取数据条数
	public Integer getCountData();
}
