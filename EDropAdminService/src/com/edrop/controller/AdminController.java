package com.edrop.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.edrop.pojo.Admin;
import com.edrop.service.AdminService;
import com.google.gson.Gson;

/**
 * admin controller
 * @ClassName:  AdminController   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月16日 下午9:36:51     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminServiceImpl;
	
	/**
	 * admin login
	 * @Title: login   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userName
	 * @param: @param password
	 * @param: @param response
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/login")
	public void login(String userName, String password, HttpServletResponse response) throws IOException {
		String res = adminServiceImpl.loginByUserNameAndPassword(userName, password);
		JSONObject json = new JSONObject();
		json.put("state", res);
		response.getWriter().print(json.toJSONString());
	}
	
	/**
	 * add admin
	 * @Title: addAdmin   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userName
	 * @param: @param password
	 * @param: @param response
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/add")
	public void addAdmin(String userName, String password, HttpServletResponse response) throws IOException {
		String res = adminServiceImpl.addAdmin(userName, password);
		JSONObject json = new JSONObject();
		json.put("state", res);
		response.getWriter().print(json.toJSONString());
	}
	
	@RequestMapping("/get_all_admin_info")
	public String getAllAdminInfo() {
		List<Admin> list = adminServiceImpl.getAllAdminInfo();
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		return ans;
	}
}
