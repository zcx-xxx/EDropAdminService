package com.edrop.service;

import com.edrop.pojo.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService {
	PageInfo<Order> getAllOrder(String number,String ouname,String outelephone,Integer page,Integer size);
}
