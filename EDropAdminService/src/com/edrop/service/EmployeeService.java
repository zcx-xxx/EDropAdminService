package com.edrop.service;

import com.edrop.pojo.Employee;
import com.edrop.pojo.User;
import com.github.pagehelper.PageInfo;

public interface EmployeeService {
	PageInfo<Employee> getAllEmployee(String username,String phone,Integer page,Integer size);
	void insertEmployee(String username,String phone,String qq,String gender,String registerTime);
	Employee findEmployeeByName(String username);
	void updateEmployee(Integer id,String username,String phone,String imgpath,String qq,String gender);
	void deleteEmployeeById(Integer id);
}
