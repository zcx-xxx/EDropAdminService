package com.edrop.service;

import com.edrop.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	PageInfo<User> getAllUser(String username,String phone,Integer page,Integer size);
}
