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

import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.dto.PostDto;
import com.posts.api.dto.util.BeanTransformer;
import com.posts.api.dto.util.DtoTransformer;
import com.posts.api.service.PostService;
import com.posts.api.service.UserService;

@Path("/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@POST
	@Path("/create/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(@PathParam("userId") Long userId, PostDto postDto) {
		try {
			User user = userService.getUser(userId);
			if (user == null)
				throw new Exception();
			Post post = DtoTransformer.transformPostDto(postDto, user);
			postService.persistPost(post);
			postDto = BeanTransformer.transformPost(post);
			return Response.ok(postDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/update/{postId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(@PathParam("postId") Long postId, PostDto postDto) {
		try {
			Post post = postService.getPost(postId);
			DtoTransformer.updatePost(post, postDto);
			postService.updatePost(post);
			postDto = BeanTransformer.transformPost(post);
			return Response.ok(postDto).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		return Response.ok("Working").build();
	}
}
