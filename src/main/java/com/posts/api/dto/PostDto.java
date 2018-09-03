package com.posts.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDto {
	private Long userId;
	private Long postId;
	private String title;
	private String topic;
	private String description;
	public PostDto() {
		super();
	}
	public PostDto(String title, String topic, String description) {
		super();
		this.title = title;
		this.topic = topic;
		this.description = description;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", title=" + title + ", topic=" + topic + ", description=" + description
				+ "]";
	}
}
