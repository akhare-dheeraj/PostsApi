package com.posts.api.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "COMMENTS")
@Entity
public class Comment implements BaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long commentId;
	
	@Column(name = "description", nullable = false)
	private String description;
	

	@OneToOne(cascade = CascadeType.PERSIST, optional = false)
	private User user;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Post post;
	
	@Column(name = "upvotes")
	private Long upvotes;
	
	@Column(name = "downvotes")
	private Long downvotes;

	public Comment() {
	}

	public Comment(String description, User user, Post post) {
		super();
		this.description = description;
		this.user = user;
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
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
		Comment other = (Comment) obj;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		return true;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Long getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(Long upvotes) {
		this.upvotes = upvotes;
	}

	public Long getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(Long downvotes) {
		this.downvotes = downvotes;
	}
	
}
