package com.edrop.service;

public interface ArticleCommentService {
	// 添加文章评论
	public void addArticleComment(Integer userId, Integer articleId, String content, Integer parentCommentId);
	
	// 获取指定文章得所有评论
	public String getAllComment(Integer articleId);
}
