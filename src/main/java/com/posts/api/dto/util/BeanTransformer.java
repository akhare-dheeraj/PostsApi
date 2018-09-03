package com.posts.api.dto.util;

import com.posts.api.beans.Comment;
import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.dto.CommentDto;
import com.posts.api.dto.PostDto;
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

	public static PostDto transformPost(Post post) {
		String title = post.getTitle();
		String topic  = post.getTopic();
		String description = post.getDescription();
		Long postid = post.getPostId();
		Long userid = post.getUser().getUserId();
		PostDto postDto = new PostDto(title, topic, description);
		postDto.setUserId(userid);
		postDto.setPostId(postid);
		return postDto;
	}
	
	public static CommentDto transformComment(Comment comment) {
		long commentId = comment.getCommentId();
		long userId = comment.getUser().getUserId();
		long postId = comment.getPost().getPostId();
		String description = comment.getDescription();
		return new CommentDto(commentId, userId, postId, description);
	}
}
