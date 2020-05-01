package com.edrop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edrop.pojo.User;
import com.edrop.service.UserService;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/list")
	public ModelAndView  userList(
			@RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phone", required = false) String phone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "2") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<User> pageInfo = userService.getAllUser(username,phone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("user_list");
		return modelAndView;
	}
	@RequestMapping("/find")
	public ModelAndView findList(
			@RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phone", required = false) String phone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<User> pageInfo = userService.getAllUser(username,phone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("user_find");
		return modelAndView;
	}
	@RequestMapping("/edit")
	public String edit(@RequestParam("username") String username,Model model) {
		User user = userService.findUserByName(username);
		model.addAttribute("user",user);
		return "user_edit";
	}

	@RequestMapping("/update")
	public String update(@RequestParam("userid") Integer id,@RequestParam("username") String username, @RequestParam("address") String address,
			@RequestParam("detailAddress") String detailAddress, @RequestParam("phone") String phone,
			@RequestParam("gender") String gender) {
		ModelAndView modelAndView = new ModelAndView();
		userService.updateUser(id, username, phone, address, detailAddress, gender);
		return "uesr_list";
	}
		
	
	
	@RequestMapping("/add")
	public String addUser(@RequestParam("username")String username,
			@RequestParam("address")String address,
			@RequestParam("detailAddress")String detailAddress,
			@RequestParam("phone")String phone ,
			@RequestParam("gender")String gender) {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		String registerTime = dateFormat.format(date); 
		userService.insertUser(username, phone, address, detailAddress, gender, registerTime);
		
		return "uesr_list";
	}
}
