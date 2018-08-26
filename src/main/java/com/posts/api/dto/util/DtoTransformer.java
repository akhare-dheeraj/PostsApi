package com.posts.api.dto.util;

import com.posts.api.beans.User;
import com.posts.api.dto.UserDto;

public class DtoTransformer {
	
	public static User transformUserDto(UserDto userDto) {
		String userName = userDto.getUserName();
		char gender = userDto.getGender();
		int age = userDto.getAge();
		User user = new User(userName, age, gender);
		user.setUserId(userDto.getUserId());
		return user;
	}

	public static void updateUser(User user, UserDto userDto) {
		user.setUserName(userDto.getUserName());
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
	}
}
