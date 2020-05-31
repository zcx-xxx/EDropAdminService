package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.Article;

public interface ArticleMapper {
	// 添加文章
	public Integer addArticle(@Param("title")String title,@Param("description")String description,@Param("contentBytes")byte[] contentBytes, @Param("decodeTable")byte[] decodeTable,@Param("time")Timestamp time);
	
	// 根据 Id 查询文章
	public Article selectArticleById(@Param("id")Integer id);
	
	// 查询文章的简要信息
	public List<Article> selectArticleBriefInfo(@Param("start")Integer start, @Param("counts")Integer counts);
	
	// 更新点赞数量
	public Integer updateLikeCounts(@Param("articleId")Integer articleId, @Param("likeDelta")Integer likeDelta);
	
	public Integer addArticleTest(@Param("contentBytes")byte[] contentBytes, 
			@Param("decodeTable")byte[] decodeTable);
}
