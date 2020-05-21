package com.edrop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.KeyWordsMapper;
import com.edrop.pojo.KeyWords;
import com.edrop.service.SensitiveWordService;
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService{
	@Resource
	private KeyWordsMapper keyWordsMapper;

	@Override
	public List<String> getKeywords() {
		List<KeyWords> list = keyWordsMapper.selectAllKeyWords();
		List<String> ans = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			ans.add(list.get(i).getKeyWord());
		}
		return ans;
	}

	@Override
	public Integer addKeyWord(String content) {
		return keyWordsMapper.addKeyWord(content);
	}
}
