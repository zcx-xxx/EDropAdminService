package com.edrop.service;

import java.util.List;

import com.edrop.pojo.Feedback;

public interface FeedbackService {
	/**
	 * 获取所有的反馈信息
	 * @Title: getAllFeedbacks   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<Feedback>      
	 * @throws
	 */
	public List<Feedback> getAllFeedbacks(Boolean isReaded);
	/**
	 * 标记已读
	 * @Title: markReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	public void markReaded(Integer feedbackId);
	/**
	 * 标记删除
	 * @Title: markDeleted   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param feedbackId      
	 * @return: void      
	 * @throws
	 */
	public void markDeleted(Integer feedbackId);
}
