package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.edrop.service.SensitiveWordService;

@Controller
@RequestMapping("/key_words")
public class KeyWordsController {
	@Resource
	private SensitiveWordService sensitiveWordServiceImpl;
	
	@RequestMapping("/add_key_word")
	public String addKeyWord(@RequestParam(name = "content")String content) {
		Integer cnt = sensitiveWordServiceImpl.addKeyWord(content);

		JSONObject json = new JSONObject();
		if (cnt > 0) json.put("state", 1); else json.put("state", 0);
		return json.toJSONString();
	}
}
