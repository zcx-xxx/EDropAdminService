package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.ArticleComment;

public interface ArticleCommentMapper {
	// 添加文章评论
	public Integer insertArticleComment(@Param("userId")Integer userId, @Param("articleId")Integer articleId,
			@Param("commentDate")Timestamp commentDate,
			@Param("commentContent")String commentContent, @Param("parentCommentId")Integer parentCommentId);
	
	// 获取文章得所有评论
	public List<ArticleComment> selectAllComment(@Param("articleId")Integer articleId);
}
