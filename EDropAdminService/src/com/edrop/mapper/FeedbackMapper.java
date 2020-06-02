package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.Feedback;

public interface FeedbackMapper {
	// 查询反馈信息，通过是否被读
	public List<Feedback> getFeedbacksByIsReaded(@Param("isReaded")Boolean isReaded);
	// 更新已读状态
	public Integer updateIsReadedState(@Param("feedbackId")Integer feedbackId);
	// 更新删除状态
	public Integer updateIsDeletedState(@Param("feedbackId")Integer feedbackId);
	// 添加反馈信息
	public Integer addFeedback(@Param("content")String content, @Param("userId")Integer userId,
			@Param("publishTime")Timestamp publishTime, @Param("isReaded")Boolean isReaded,
			@Param("isDeleted")Boolean isDeleted);
	// 查询已经删除的反馈
	public List<Feedback> selectDeletedFeedback();
	// 还原已经删除的
	public Integer updateDeletedToNoReaded(@Param("feedbackId")Integer feedbackId);
	// 获得指定状态信息的数目
	public Integer selectFeedbackCountsByState(@Param("isReaded")Boolean isReaded,
			@Param("isDeleted")Boolean isDeleted);
}
