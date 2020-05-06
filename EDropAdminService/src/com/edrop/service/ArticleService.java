package com.edrop.service;

import com.edrop.pojo.Article;
import com.github.pagehelper.PageInfo;

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
	// 分页查询文章列表
	public PageInfo<Article> getAllArticleByPage(Integer page, Integer size);
	// 根据 Id 删除对应的文章
	public void deleteArticleById(Integer valueOf);
}
