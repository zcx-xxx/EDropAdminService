package com.edrop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.KeyWords;

public interface KeyWordsMapper {
//	获取所有得敏感词
	public List<KeyWords> selectAllKeyWords();
//	添加敏感词
	public Integer addKeyWord(@Param("keyWord")String keyWord);
//	根据内容查询敏感词
	public KeyWords selectKeyWordsByContent(@Param("content")String content);
}
