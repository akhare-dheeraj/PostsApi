package com.posts.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {

	private long commentId;
	private long userId;
	private long postId;
	private String description;

	public CommentDto() {
		super();
	}
	
	public CommentDto(String description) {
		this.description = description;
	}

	public CommentDto(long commentId, long userId, long postId, String description) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.postId = postId;
		this.description = description;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
