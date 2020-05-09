package com.edrop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edrop.mapper.EmployeeMapper;
import com.edrop.mapper.RubbishMapper;
import com.edrop.pojo.Employee;
import com.edrop.pojo.Rubbish;
import com.edrop.service.EmployeeService;
import com.edrop.service.RubbishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RubbishServiceImpl implements RubbishService {
	
	@Autowired
	private RubbishMapper rubbishMapper;

	public PageInfo<Rubbish> getAllRubbish(String name, String type, Integer page, Integer size) {
		PageHelper.startPage(page,size);	
		Integer typeId = null;
		if (type != "" && type != null) {
			if (type.equals("可回收物")) {
				typeId = 1;
			}else if (type.equals("有害垃圾")) {
				typeId = 2;
			}else if (type.equals("湿垃圾")) {
				typeId = 3;
			}else if (type.equals("干垃圾")) {
				typeId = 4;
			}
		}
		
		List<Rubbish> rubbishs =  rubbishMapper.selectAllRubbish(name,typeId);
		PageInfo<Rubbish> info = new PageInfo<Rubbish>(rubbishs);		
		return info;
	}

	public void insertRubbish(String name, String type) {
		Integer typeId = null;
		if (type != "" && type != null) {
			if (type.equals("可回收物")) {
				typeId = 1;
			}else if (type.equals("有害垃圾")) {
				typeId = 2;
			}else if (type.equals("湿垃圾")) {
				typeId = 3;
			}else if (type.equals("干垃圾")) {
				typeId = 4;
			}
		}
		rubbishMapper.insertRubbish(name,typeId);
	}

	public Rubbish findRubbishById(Integer id) {
		return rubbishMapper.selRubbishById(id);
	}

}
