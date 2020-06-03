package com.edrop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.AdminMapper;
import com.edrop.pojo.Admin;
import com.edrop.pojo.User;
import com.edrop.service.AdminService;
import com.edrop.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * admin service implement
 * @ClassName:  AdminServiceImpl   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月16日 下午9:39:46     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
@Service
public class AdminServiceImpl implements AdminService{
	@Resource
	private AdminMapper adminMapper;

	@Override
	public String loginByUserNameAndPassword(String userName, String password) {
		String state = "";
		// 查询用户名和密码是否匹配
		if (password != null && password.length() > 0) {
			password = MD5Utils.MD5Encode(password);
			Admin admin = adminMapper.selectAdminByUserNameAndPassword(userName, password);
			if (admin != null) {
				// 匹配更新最后登录时间
				adminMapper.updateLastLoginTime(userName,new Timestamp(System.currentTimeMillis()));
				state = "success";
			} else {
				state = "fail";
			}
		} else {
			state = "fail";
		}
		
		return state;
	}

	@Override
	public String addAdmin(String userName, String password) {
		// 查询用户名是否已经存在
		String state = "";
		Admin admin = adminMapper.selectAdminByUserName(userName);
		if (admin != null) {
			state = "fail";
		} else {       // 不存在添加
			if (password == null || password.length() == 0) System.out.println("password exception!!!");
			password = MD5Utils.MD5Encode(password);
			adminMapper.insertAdmin(userName, password, new Timestamp(System.currentTimeMillis()), 
					new Timestamp(System.currentTimeMillis()));
			state = "success";
		}
		return state;
	}

	@Override
	public List<Admin> getAllAdminInfo() {
		List<Admin> ans = adminMapper.selectAllAdminInfo();
		return ans;
	}
	public PageInfo<Admin> getAllAdmin(String username,Integer page,Integer size){
			
			PageHelper.startPage(page,size);
			
			List<Admin> admins =  adminMapper.selectAllAdmin(username);
			PageInfo<Admin> info = new PageInfo<Admin>(admins);
			
			return info;
		}
	
}
