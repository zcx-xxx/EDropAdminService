package com.edrop.utils;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.edrop.mapper.UserPlaceOrderAddressInfoMapper;

public class ThredQuery<E> implements Callable<E>{
	private E ans;
	private UserPlaceOrderAddressInfoMapper mapper;
	
	public ThredQuery(UserPlaceOrderAddressInfoMapper mapper, Integer begin, Integer count) {
		this.mapper = mapper;	
		ans = (E) mapper.selectOrderAddressInfoByPage(begin, count);
	}

	@Override
	public E call() throws Exception {
		return ans;
	}
}
