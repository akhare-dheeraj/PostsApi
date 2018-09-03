package com.post.api.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import com.posts.api.dto.CommentDto;
import com.posts.api.dto.PostDto;
import com.posts.api.dto.UserDto;
import com.posts.api.resource.CommentResource;
import com.posts.api.resource.PostResource;
import com.posts.api.resource.UserResource;

public abstract class BaseResourceTest extends JerseyTest{
	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(UserResource.class, PostResource.class, CommentResource.class).property("contextConfigLocation",
				"classpath:application-context-test.xml").register(LoggingFilter.class);
	}
	
	protected PostDto createPostDto() {
		PostDto postDto = new PostDto("Women Power", "Feminism", "I hate feminism");
		return postDto;
	}
	protected UserDto createUserDto() {
		UserDto userDto = new UserDto("UserName", 'M', 24);
		return userDto;
	}
	
	protected CommentDto createCommentDto() {
		CommentDto commentDto = new CommentDto("I am adding a comment");
		return commentDto;
	}
}
