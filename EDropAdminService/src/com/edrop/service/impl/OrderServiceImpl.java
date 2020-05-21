package com.edrop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edrop.mapper.OrderMapper;
import com.edrop.pojo.Order;
import com.edrop.pojo.User;
import com.edrop.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	public PageInfo<Order> getAllOrder(String number,String ouname, String outelephone, Integer page, Integer size) {
			
			PageHelper.startPage(page,size);
			
			List<Order> orders =  orderMapper.selectAllOrder(number,ouname,outelephone);
			PageInfo<Order> info = new PageInfo<Order>(orders);
			
			return info;
		
	}

}
