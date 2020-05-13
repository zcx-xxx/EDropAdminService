package com.edrop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.FeedbackMapper;
import com.edrop.pojo.Feedback;
import com.edrop.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Resource
	private FeedbackMapper feedbackMapper;
	@Override
	public List<Feedback> getAllFeedbacks(Boolean isReaded) {
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
}
