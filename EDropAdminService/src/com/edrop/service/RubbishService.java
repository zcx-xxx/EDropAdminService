package com.edrop.service;
import com.edrop.pojo.Rubbish;
import com.github.pagehelper.PageInfo;

public interface RubbishService {

	PageInfo<Rubbish> getAllRubbish(String name, String type, Integer page, Integer size);

	void insertRubbish(String name, String type);

	Rubbish findRubbishById(Integer id);

}
