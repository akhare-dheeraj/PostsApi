package com.posts.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	private Long userId;
	private String userName;
	private char gender;
	private int age;
	
	public UserDto() {
		super();
	}
	public UserDto(String userName, char gender, int age) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.age = age;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", age=" + age + "]";
	}
	
}
