package com.post.api.resource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.posts.api.dto.PostDto;
import com.posts.api.dto.UserDto;


public class PostResourceTest extends BaseResourceTest {
	
	@Test
	public void testAddPost() {
		UserDto userDto = createUserDto();
		Response response = target("/users/create").request().post(Entity.entity(userDto, MediaType.APPLICATION_JSON));
		userDto = response.readEntity(UserDto.class);
		PostDto postDto = createPostDto();
		response = target("/posts/create/"+userDto.getUserId()).request().post(Entity.entity(postDto, MediaType.APPLICATION_JSON));
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatus());
	}
	
}
