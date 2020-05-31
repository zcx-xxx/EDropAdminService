package com.edrop.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserArticleIsLikeMapper {
	// 获取用户和文章是否有对应关系
	public Integer selectUserArticleIsExits(@Param("userId")Integer userId, @Param("articleId")Integer articleId);
	
	// 添加项
	public Integer insertItem(@Param("userId")Integer userId, @Param("articleId")Integer articleId);
	
	// 删除项
	public Integer deleteItem(@Param("userId")Integer userId, @Param("articleId")Integer articleId);
}
