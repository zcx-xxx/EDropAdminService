package com.edrop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edrop.mapper.UserMapper;
import com.edrop.pojo.User;
import com.edrop.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public PageInfo<User> getAllUser(String username,String phone,Integer page,Integer size){
		
		PageHelper.startPage(page,size);
		
		List<User> users =  userMapper.selectAllUser(username,phone);
		PageInfo<User> info = new PageInfo<User>(users);
		
		return info;
	}
}
