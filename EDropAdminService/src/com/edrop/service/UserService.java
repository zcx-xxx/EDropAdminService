package com.edrop.service;

import com.edrop.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	PageInfo<User> getAllUser(Integer page,Integer size);
}
