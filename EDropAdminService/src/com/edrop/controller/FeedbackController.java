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
		System.out.println(isReaded);
		List<Feedback> list = feedbackServiceImpl.getAllFeedbacks(Boolean.valueOf(isReaded.equals("1")));
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		System.out.println(ans);
		return ans;
	}
	/**
	 * 获得已经删除的反馈
	 * @Title: getDeletedFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/get_deleted_feedback")
	public String getDeletedFeedback() {
		List<Feedback> list = feedbackServiceImpl.getDeletedFeedback();
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		System.out.println(ans);
		return ans;
	}
	/**
	 * 还原已经删除的
	 * @Title: getDeletedFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/restore_deleted_feedback")
	public String restoreDeletedFeedback(String feedbackId) {
		feedbackServiceImpl.restoreDeletedFeedback(Integer.valueOf(feedbackId));
		JSONObject json = new JSONObject();
		json.put("state", "success");
		return json.toString();
	}
	
	
	/**
	 * 标记已读
	 * @Title: markReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/mark_readed")
	public String markReaded(Integer feedbackId) {
		feedbackServiceImpl.markReaded(feedbackId);
		JSONObject json = new JSONObject();
		json.put("state", "success");
		return json.toString();
	}
	/**
	 * 标记已删除
	 * @Title: deleteReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/delete_readed")
	public String deleteReaded(Integer feedbackId) {
		feedbackServiceImpl.markDeleted(feedbackId);
		JSONObject json = new JSONObject();
		json.put("state", "success");
		return json.toString();
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
	
	@ResponseBody
	@RequestMapping("/get_diff_feedback_counts")
	public String getDiffFeedbackCounts() {
		String ans = feedbackServiceImpl.getDiffFeedbackCounts();
		return ans;
	}
}
