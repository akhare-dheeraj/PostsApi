package com.posts.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.posts.api.beans.User;
import com.posts.api.dto.UserDto;
import com.posts.api.dto.util.BeanTransformer;
import com.posts.api.dto.util.DtoTransformer;
import com.posts.api.service.UserService;

@Path("/users")
public class UserResource {
	@Autowired
	private UserService userService;

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(UserDto userDto) {
		User user = DtoTransformer.transformUserDto(userDto);
		try {
			userService.persistUser(user);
			userDto.setUserId(user.getUserId());
			return Response.ok(BeanTransformer.transformUser(user)).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/delete/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userId") Long userId) {
		try {
			userService.deleteUser(userId);
			JsonObject obj = new JsonObject();
			obj.addProperty("success", "true");
			return Response.ok(obj).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/update/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("userId") Long userId, UserDto userDto) {
		try {
			User user = userService.getUser(userId);
			if (user == null)
				throw new Exception();
			DtoTransformer.updateUser(user, userDto);
			UserDto responseDto = BeanTransformer.transformUser(user);
			return Response.ok(responseDto).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userId") Long userId) {
		try {
			User user = userService.getUser(userId);
			if (user == null)
				throw new Exception();
			UserDto userDto = BeanTransformer.transformUser(user);
			return Response.ok(userDto).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
