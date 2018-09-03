package com.posts.api.dto.util;

import com.posts.api.beans.Comment;
import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.dto.CommentDto;
import com.posts.api.dto.PostDto;
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

	public static Post transformPostDto(PostDto postDto, User user) {
		String title = postDto.getTitle();
		String topic = postDto.getTopic();
		String description = postDto.getDescription();
		Post post = new Post(title, topic, description, user);
		return post;
	}

	public static void updatePost(Post post, PostDto postDto) {
		post.setTitle(postDto.getTitle());
		post.setTopic(postDto.getTopic());
		post.setDescription(postDto.getDescription());
	}

	public static Comment transformCommentDto(CommentDto commentDto, User user, Post post) {
		Comment cmt = new Comment();
		cmt.setDescription(commentDto.getDescription());
		cmt.setUser(user);
		cmt.setPost(post);
		return cmt;
	}

	public static void updateComment(Comment comment, CommentDto commentDto) {
		comment.setDescription(commentDto.getDescription());
	}
}
