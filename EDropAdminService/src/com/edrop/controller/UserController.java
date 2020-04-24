package com.edrop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edrop.pojo.User;
import com.edrop.service.UserService;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/list")
	public String userList( Model model,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "2") Integer size) {
		PageInfo<User> pageInfo = userService.getAllUser(page,size);
		model.addAttribute("pageInfo",pageInfo);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getList().toString());
		return "user_list";
	}
}
