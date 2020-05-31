package com.edrop.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edrop.service.SensitiveWordService;
import com.edrop.utils.ACFind;

@Controller
@RequestMapping("/key_words")
public class KeyWordsController {
	@Resource
	private SensitiveWordService sensitiveWordServiceImpl;
	@Resource
	private ACFind aCFind;
	
	@ResponseBody
	@RequestMapping("/add_key_word")
	public String addKeyWord(@RequestParam(name = "content")String content) {
		Integer cnt = sensitiveWordServiceImpl.addKeyWord(content);

		JSONObject json = new JSONObject();
		if (cnt > 0) json.put("state", "success"); else json.put("state", "fail");
		return json.toJSONString();
	}
	
	@RequestMapping("/filter_key_word")
	public void filterKeyWords(@RequestParam(name = "comment")String comment, HttpServletResponse response) throws IOException {
		String ans = aCFind.sensitiveFilter(comment);
		JSONObject json = new JSONObject();
		json.put("comment", ans);
		response.getWriter().write(json.toString());
	}
}
