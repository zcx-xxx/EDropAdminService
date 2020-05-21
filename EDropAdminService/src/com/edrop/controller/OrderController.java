package com.edrop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edrop.pojo.Order;
import com.edrop.service.OrderService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping("/list")
	public ModelAndView  userList(
			@RequestParam(name = "number", required = false) String number,
			@RequestParam(name = "ouname", required = false) String ouname,
            @RequestParam(name = "outelephone", required = false) String outelephone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Order> pageInfo = orderService.getAllOrder(number,ouname,outelephone,page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("order_list");
		return modelAndView;
	}
	@RequestMapping("/find")
	public ModelAndView  findList(
			@RequestParam(name = "number", required = false) String number,
			@RequestParam(name = "ouname", required = false) String ouname,
            @RequestParam(name = "outelephone", required = false) String outelephone ,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Order> pageInfo = orderService.getAllOrder(number,ouname,outelephone,page,size);
		System.out.println(pageInfo.getList().toString());
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("order_find");
		return modelAndView;
	}
}
