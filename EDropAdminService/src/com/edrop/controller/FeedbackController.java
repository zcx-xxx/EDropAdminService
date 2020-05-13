package com.edrop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edrop.pojo.Feedback;
import com.edrop.service.FeedbackService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackServiceImpl;
	
	@ResponseBody
	@RequestMapping("/get_feedback")
	public String getAllFeedbackByIsReaded(@RequestParam(defaultValue = "0")String isReaded) {
		List<Feedback> list = feedbackServiceImpl.getAllFeedbacks(Boolean.valueOf(isReaded));
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		System.out.println(ans);
		return ans;
	}
	
	@RequestMapping("/mark_readed")
	public void markReaded(Integer feedbackId) {
		feedbackServiceImpl.markReaded(feedbackId);
		return;
	}
	
	@RequestMapping("/delete_readed")
	public void deleteReaded(Integer feedbackId) {
		feedbackServiceImpl.markDeleted(feedbackId);
		return;
	}
}
