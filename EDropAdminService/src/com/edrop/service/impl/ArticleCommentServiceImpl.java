package com.edrop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.ArticleCommentMapper;
import com.edrop.mapper.ArticleMapper;
import com.edrop.pojo.ArticleComment;
import com.edrop.service.ArticleCommentService;
import com.google.gson.Gson;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService{
	@Resource
	private ArticleCommentMapper articleCommentMapper;
	@Resource
	private ArticleMapper articleMapperImpl;
	// 添加文章评论
//	public Integer insertArticleComment(@Param("userId")Integer userId, @Param("articleId")Integer articleId,
//			 @Param("commentDate")Timestamp commentDate,
//			@Param("commentContent")String commentContent, @Param("parentCommentId")Integer parentCommentId);
//	
//	// 获取文章得所有评论
//	public List<ArticleComment> selectAllComment(@Param("articleId")Integer articleId);

	@Override
	public void addArticleComment(Integer userId, Integer articleId, String content, Integer parentCommentId) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		articleCommentMapper.insertArticleComment(userId, articleId, stamp, content, parentCommentId);
		articleMapperImpl.updateCommentCounts(articleId, 1);
		return;
	}

	@Override
	public String getAllComment(Integer articleId) {
		List<ArticleComment> list = articleCommentMapper.selectAllComment(articleId);
		Gson gson = new Gson();
		String ans = gson.toJson(list);
		return ans;
	}
}
