package com.post.api.resource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.posts.api.dto.UserDto;

public class UserResourceTest extends BaseResourceTest {
	@Test
	public void testCreateUser() {
		UserDto dto = createUserDto();
		Response response = target("/users/create").request().post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		System.out.println(response);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void testDeleteUser() {
		UserDto dto = createUserDto();
		Response createUserResponse = target("/users/create").request().post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		UserDto responseDto = createUserResponse.readEntity(UserDto.class);
		Long userid = responseDto.getUserId();
		Response response = target("users/delete/"+userid).request().get();
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testUpdateUser() {
		UserDto dto = createUserDto();
		Response createUserResponse = target("/users/create").request().post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		UserDto responseDto = createUserResponse.readEntity(UserDto.class);
		Long userid = responseDto.getUserId();
		dto.setUserName("NewUser");
		Response response = target("users/update/"+userid).request().post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		responseDto = response.readEntity(UserDto.class);
		Assert.assertEquals("NewUser", responseDto.getUserName());
	}
	
	@Test
	public void testGetUser() {
		UserDto dto = createUserDto();
		Response createUserResponse = target("/users/create").request().post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		UserDto responseDto = createUserResponse.readEntity(UserDto.class);
		Long userid = responseDto.getUserId();
		Response response = target("/users/"+userid).request().get();
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatus());
	}
	private UserDto createUserDto() {
		UserDto userDto = new UserDto("UserName", 'M', 24);
		return userDto;
	}
}
