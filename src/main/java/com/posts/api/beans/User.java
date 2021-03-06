package com.posts.api.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "USERS")
@Entity
public class User extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2732872247175803058L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long userId;
	
	@Column(name = "name", nullable = false)
	private String userName;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "gender")
	private char gender;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Post> posts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Comment> comments;

	public User() {
	}

	public User(String userName, int age, char gender) {
		super();
		this.userName = userName;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	
	public List<Post> getPosts() {
		posts = posts==null?new ArrayList<>():posts;
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", age=" + age + ", gender=" + gender + ", posts="
				+ posts + "]";
	}

	public List<Comment> getComments() {
		comments = comments==null?new ArrayList<>():comments;
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
