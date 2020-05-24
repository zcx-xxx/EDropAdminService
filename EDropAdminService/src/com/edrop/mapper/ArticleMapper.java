package com.edrop.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.Article;

public interface ArticleMapper {
	// 添加文章
	public Integer addArticle(@Param("title")String title,@Param("description")String description,@Param("contentBytes")byte[] contentBytes, @Param("decodeTable")byte[] decodeTable,@Param("time")Timestamp time);
	
	// 根据 Id 查询文章
	public Article selectArticleById(@Param("id")Integer id);
}