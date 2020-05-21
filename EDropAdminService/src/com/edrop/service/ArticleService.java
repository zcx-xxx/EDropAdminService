package com.edrop.service;

public interface ArticleService {
	// 添加文章
	public Integer addArticle(String content);
	// id 查询文章
	public String selectArticleById(Integer id);
}
