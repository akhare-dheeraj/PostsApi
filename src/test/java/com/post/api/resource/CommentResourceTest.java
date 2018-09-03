package com.post.api.resource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.posts.api.dto.CommentDto;
import com.posts.api.dto.PostDto;
import com.posts.api.dto.UserDto;

import org.junit.Assert;

public class CommentResourceTest extends BaseResourceTest {

	@Test
	public void testAddComment() {
		UserDto userDto = createUserDto();
		Response response = target("/users/create").request().post(Entity.entity(userDto, MediaType.APPLICATION_JSON));
		userDto = response.readEntity(UserDto.class);
		PostDto postDto = createPostDto();
		response = target("/posts/create/" + userDto.getUserId()).request()
				.post(Entity.entity(postDto, MediaType.APPLICATION_JSON));
		postDto = response.readEntity(PostDto.class);
		CommentDto commentDto = createCommentDto();
		commentDto.setUserId(userDto.getUserId());
		commentDto.setPostId(postDto.getPostId());
		response = target("/comments/add/").queryParam("userId", userDto.getUserId())
				.queryParam("postId", postDto.getPostId()).request()
				.post(Entity.entity(commentDto, MediaType.APPLICATION_JSON));
		Assert.assertNotNull(postDto);
		Assert.assertEquals(200, response.getStatus());
	}

}
