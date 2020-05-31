package com.edrop.service;

import com.edrop.pojo.Article;

public interface ArticleService {
	// 添加文章
	public Integer addArticle(Article article);
	// id 查询文章
	public String selectArticleById(Integer id);
	
	// 获得文章简要信息
	public String getArticleBriefInfo(Integer userId, Integer pageNum, Integer counts);
	
	// 点赞或者取消点赞
	public Integer likeOrCancelLike(Integer userId, Integer articleId);
	
	public Integer addArticleTest(String content);
}
