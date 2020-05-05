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

	public void insertUser(String username, String phone, String address, String detailAddress, String gender,
			String registerTime) {
		String defaultPasswordString = "e10adc3949ba59abbe56e057f20f883e";
		userMapper.insUserInfo(phone, username, defaultPasswordString, "/img", "defaulthead.jpg", address, detailAddress, gender, registerTime);
	}

	public User findUserByName(String username) {
		return userMapper.selUserByUsername(username);
	}

	public void updateUser(Integer id,String username, String phone, String address, String detailAddress, String gender) {
		userMapper.upUserInfo(id, phone, username, address, detailAddress, gender);		
	}

	public void deleteById(Integer id) {
		userMapper.deleteByUserId(id);
	}
}
