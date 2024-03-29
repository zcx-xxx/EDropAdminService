package com.edrop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.edrop.mapper.FeedbackMapper;
import com.edrop.pojo.Feedback;
import com.edrop.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Resource
	private FeedbackMapper feedbackMapper;
	@Override
	public List<Feedback> getAllFeedbacks(Boolean isReaded) {
		System.out.println(isReaded);
		return feedbackMapper.getFeedbacksByIsReaded(isReaded);
	}
	@Override
	public void markReaded(Integer feedbackId) {
		feedbackMapper.updateIsReadedState(feedbackId);
		return;
	}
	@Override
	public void markDeleted(Integer feedbackId) {
		feedbackMapper.updateIsDeletedState(feedbackId);
		return;
	}
	@Override
	public void addFeedback(String content, Integer userId) {
		feedbackMapper.addFeedback(content, userId, new Timestamp(System.currentTimeMillis()), false, false);
		return;
	}
	@Override
	public List<Feedback> getDeletedFeedback() {
		List<Feedback> ans = feedbackMapper.selectDeletedFeedback();
		return ans;
	}
	@Override
	public Integer restoreDeletedFeedback(Integer feedbackId) {
		Integer ans = feedbackMapper.updateDeletedToNoReaded(feedbackId);
		return null;
	}
	@Override
	public String getDiffFeedbackCounts() {
		JSONObject ans = new JSONObject();
		Integer cnt = 0;
		cnt = feedbackMapper.selectFeedbackCountsByState(false, false);
		ans.put("no_read", cnt);
		cnt = feedbackMapper.selectFeedbackCountsByState(true, false);
		ans.put("readed", cnt);
		cnt = feedbackMapper.selectFeedbackCountsByState(true, true);
		ans.put("deleted", cnt);
		return ans.toString();
	}
}
