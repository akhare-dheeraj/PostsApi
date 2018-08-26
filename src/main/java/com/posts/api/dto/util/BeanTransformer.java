package com.posts.api.dto.util;

import com.posts.api.beans.User;
import com.posts.api.dto.UserDto;

public class BeanTransformer {
	public static UserDto transformUser(User user) {
		String userName = user.getUserName();
		Long userId = user.getUserId();
		int age = user.getAge();
		char gender = user.getGender();
		UserDto userDto = new UserDto(userName, gender, age);
		userDto.setUserId(userId);
		return userDto;
	}
}
