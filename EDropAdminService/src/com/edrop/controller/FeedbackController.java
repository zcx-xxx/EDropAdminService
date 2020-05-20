package com.edrop.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edrop.pojo.Feedback;
import com.edrop.service.FeedbackService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackServiceImpl;
	/**
	 * 获取所有的反馈信息
	 * @Title: getAllFeedbackByIsReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param isReaded
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/get_feedback")
	public String getAllFeedbackByIsReaded(@RequestParam(defaultValue = "0")String isReaded) {
		List<Feedback> list = feedbackServiceImpl.getAllFeedbacks(Boolean.valueOf(isReaded));
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		System.out.println(ans);
		return ans;
	}
	/**
	 * 标记已读
	 * @Title: markReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/mark_readed")
	public void markReaded(Integer feedbackId) {
		feedbackServiceImpl.markReaded(feedbackId);
		return;
	}
	/**
	 * 标记已删除
	 * @Title: deleteReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/delete_readed")
	public void deleteReaded(Integer feedbackId) {
		feedbackServiceImpl.markDeleted(feedbackId);
		return;
	}
	/**
	 * @throws IOException 
	 * 添加反馈信息
	 * @Title: addFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param content
	 * @param: @param userId      
	 * @return: void      
	 * @throws
	 */
//	@ResponseBody
	@RequestMapping(value = "/add_feedback")
	public void addFeedback(HttpServletResponse response, HttpServletRequest request) throws IOException {
		Logger log = Logger.getLogger(FeedbackController.class);
		log.info("feedback: 收到请求信息");
		// 获取参数
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		String qq = request.getParameter("qq");
		String phone = request.getParameter("phone");
		// 处理
		feedbackServiceImpl.addFeedback(content, Integer.valueOf(userId));
		// 返回数据
		JSONObject json = new JSONObject();
		json.put("state", 1);
		response.getWriter().print(json.toJSONString());
	}
}
