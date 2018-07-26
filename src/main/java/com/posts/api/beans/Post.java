package com.posts.api.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "POSTS")
@Entity
public class Post implements BaseBean{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long postId;
	
	@Column(name = "title", nullable=false)
	private String title;
	
	@NotNull
	private String topic;
	
	@NotNull
	private String description;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
	private Set<Comment> comments;
	
	@Column
	private long upvotes;
	
	@Column
	private long downvotes;

	public Post() {
	}

	public Post(String title, String topic, String description, User user) {
		super();
		this.title = title;
		this.topic = topic;
		this.description = description;
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public long getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(long upvotes) {
		this.upvotes = upvotes;
	}

	public long getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(long downvotes) {
		this.downvotes = downvotes;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", topic=" + topic + ", description=" + description
				+ ", user=" + user + ", comments=" + comments + ", upvotes=" + upvotes + ", downvotes=" + downvotes
				+ "]";
	}

}
