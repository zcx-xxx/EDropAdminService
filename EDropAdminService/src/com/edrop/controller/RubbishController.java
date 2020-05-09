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
import com.edrop.pojo.Rubbish;
import com.edrop.service.RubbishService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/rubbish")
public class RubbishController {
	@Autowired
	private RubbishService rubbishService ;
	@RequestMapping("/list")
	public ModelAndView  rubbishList(
			@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "type", required = false) String type ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "8") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Rubbish> pageInfo = rubbishService.getAllRubbish(name,type,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("rubbish_list");
		return modelAndView;
	}
	
	@RequestMapping("/find")
	public ModelAndView findList(
			@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "type", required = false) String type ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "8") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Rubbish> pageInfo = rubbishService.getAllRubbish(name,type,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("rubbish_find");
		return modelAndView;
	}
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(@RequestParam("name")String name,
			@RequestParam("type")String type) {
		rubbishService.insertRubbish(name,type);
		return "success";
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam("id")Integer id,Model model) {
		Rubbish rubbish = rubbishService.findRubbishById(id);
		model.addAttribute("rubbish",rubbish);
		return "rubbish_edit";
	}

//	@RequestMapping("/update")
//	@ResponseBody
//	public String update(@RequestParam("userid") Integer id,@RequestParam("username") String username, @RequestParam("qq") String qq,
//			@RequestParam("imgpath") String imgpath, @RequestParam("phone") String phone,
//			@RequestParam("gender") String gender) {
//		employeeService.updateEmployee(id, username, phone, imgpath, qq, gender);
//		return "success";
//	}
}
