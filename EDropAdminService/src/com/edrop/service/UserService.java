package com.edrop.service;

import com.edrop.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	PageInfo<User> getAllUser(String username,String phone,Integer page,Integer size);
	void insertUser(String username,String phone,String address,String detailAddress,String gender,String registerTime);
	User findUserByName(String username);
	void updateUser(Integer id,String username,String phone,String address,String detailAddress,String gender);
}
