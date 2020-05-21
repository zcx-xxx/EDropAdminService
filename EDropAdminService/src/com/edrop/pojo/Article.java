package com.edrop.pojo;

import java.sql.Timestamp;

public class Article {
	private Integer id;
	private String articleTitle;
	private String articleDescription;
	private Integer tagId;
	private byte[] contentBytes;
	private byte[] decodeTable;
	private Integer userId;
	private Timestamp publishDate;
	private Integer articleLikeCount;
	private Integer articleCommentCount;
	private Integer articleViews;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleDescription() {
		return articleDescription;
	}
	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public byte[] getContentBytes() {
		return contentBytes;
	}
	public void setContentBytes(byte[] contentBytes) {
		this.contentBytes = contentBytes;
	}
	public byte[] getDecodeTable() {
		return decodeTable;
	}
	public void setDecodeTable(byte[] decodeTable) {
		this.decodeTable = decodeTable;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getArticleLikeCount() {
		return articleLikeCount;
	}
	public void setArticleLikeCount(Integer articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}
	public Integer getArticleCommentCount() {
		return articleCommentCount;
	}
	public void setArticleCommentCount(Integer articleCommentCount) {
		this.articleCommentCount = articleCommentCount;
	}
	public Integer getArticleViews() {
		return articleViews;
	}
	public void setArticleViews(Integer articleViews) {
		this.articleViews = articleViews;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
