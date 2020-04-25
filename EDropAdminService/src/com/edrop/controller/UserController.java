package com.edrop.controller;

import java.util.HashMap;
import java.util.List;

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
            @RequestParam(name = "size", defaultValue = "2") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<User> pageInfo = userService.getAllUser(username,phone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("user_find");
		return modelAndView;
	}
}
