package com.edrop.service;

import com.edrop.pojo.Article;

public interface ArticleService {
	// 添加文章
	public Integer addArticle(Article article);
	// id 查询文章
	public String selectArticleById(Integer id);
}
