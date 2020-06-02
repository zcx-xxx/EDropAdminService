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
	
	/**
	 * 添加反馈信息
	 * @Title: addFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param content
	 * @param: @param userId      
	 * @return: void      
	 * @throws
	 */
	public void addFeedback(String content, Integer userId);
	/**
	 * 获得已经删除的反馈
	 * @Title: getDeletedFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<Feedback>      
	 * @throws
	 */
	public List<Feedback> getDeletedFeedback();
	/**
	 * 还原已经删除的反馈
	 * @Title: restoreDeletedFeedback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	public Integer restoreDeletedFeedback(Integer feedbackId);
	/**
	 * 获得不同的反馈的数量
	 * @Title: getDiffFeedbackCounts   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getDiffFeedbackCounts();
}
