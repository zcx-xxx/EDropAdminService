package com.edrop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edrop.mapper.EmployeeMapper;
import com.edrop.pojo.Employee;
import com.edrop.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	public PageInfo<Employee> getAllEmployee(String username, String phone, Integer page, Integer size) {
		PageHelper.startPage(page,size);	
		List<Employee> employees =  employeeMapper.selectAllEmployee(username,phone);
		PageInfo<Employee> info = new PageInfo<Employee>(employees);		
		return info;
	}

	public void insertEmployee(String username, String phone, String qq, String gender,String registerTime) {
		String defaultPasswordString = "e10adc3949ba59abbe56e057f20f883e";
		employeeMapper.insEmployeeInfo(phone, username, defaultPasswordString, "/imgs", qq,"defaulthead.jpg", gender, registerTime);
	}

	public Employee findEmployeeByName(String username) {
		return employeeMapper.selEmployeeByUsername(username);
	}

	public void updateEmployee(Integer id,String username,String phone,String imgpath,String qq,String gender) {
		employeeMapper.upEmployeeInfo(id, phone, username, imgpath, qq, gender);			
	}
	public void deleteEmployeeById(Integer id) {
		employeeMapper.deleteByEmployeeId(id);
	}

}
