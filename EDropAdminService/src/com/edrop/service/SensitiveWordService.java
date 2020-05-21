package com.edrop.service;

import java.util.List;

public interface SensitiveWordService {
//	获取所有得敏感词
	public List<String> getKeywords();
	
//	添加敏感词
	public Integer addKeyWord(String content);
}
