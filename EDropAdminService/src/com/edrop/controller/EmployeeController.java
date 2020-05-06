package com.edrop.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.edrop.pojo.Employee;
import com.edrop.pojo.User;
import com.edrop.service.EmployeeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping("/list")
	public ModelAndView  userList(
			@RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phone", required = false) String phone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Employee> pageInfo = employeeService.getAllEmployee(username,phone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("employee_list");
		return modelAndView;
	}
	@RequestMapping("/find")
	public ModelAndView findList(
			@RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "phone", required = false) String phone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Employee> pageInfo = employeeService.getAllEmployee(username,phone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("employee_find");
		return modelAndView;
	}
	@RequestMapping("/edit")
	public String edit(@RequestParam("username") String username,Model model) {
		Employee employee = employeeService.findEmployeeByName(username);
		model.addAttribute("employee",employee);
		return "employee_edit";
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestParam("userid") Integer id,@RequestParam("username") String username, @RequestParam("qq") String qq,
			@RequestParam("imgpath") String imgpath, @RequestParam("phone") String phone,
			@RequestParam("gender") String gender) {
		employeeService.updateEmployee(id, username, phone, imgpath, qq, gender);
		return "success";
	}
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(@RequestParam("username")String username,
			@RequestParam("phone")String phone,
			@RequestParam("qq")String qq ,
			@RequestParam("gender")String gender) {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		String registerTime = dateFormat.format(date); 
		employeeService.insertEmployee(username, phone, qq, gender, registerTime);
		
		return "success";
	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("userid") Integer id,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		employeeService.deleteEmployeeById(id);
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Employee> pageInfo = employeeService.getAllEmployee(null,null,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("employee_find");
		return modelAndView;
	}
}
