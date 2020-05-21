package com.edrop.mapper;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.Article;

public interface ArticleMapper {
	// 添加文章
	public Integer addArticle(@Param("contentBytes")byte[] contentBytes, @Param("decodeTable")byte[] decodeTable);
	
	// 根据 Id 查询文章
	public Article selectArticleById(@Param("id")Integer id);
}
