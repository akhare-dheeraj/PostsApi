package com.posts.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.posts.api.beans.Comment;
import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.dto.CommentDto;
import com.posts.api.dto.util.BeanTransformer;
import com.posts.api.dto.util.DtoTransformer;
import com.posts.api.service.CommentService;
import com.posts.api.service.PostService;
import com.posts.api.service.UserService;

@Path("/comments")
public class CommentResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@QueryParam("postId") Long postId, @QueryParam("userId") Long userId, CommentDto commentDto) {
		try {
			Post post = postService.getPost(postId);
			User user = userService.getUser(userId);
			if (post == null || user == null)
				throw new Exception("Post/User not found");
			Comment comment = DtoTransformer.transformCommentDto(commentDto, user, post);
			commentService.persistComment(comment);
			commentDto = BeanTransformer.transformComment(comment);
			return Response.ok(commentDto).build();
		} catch (Exception e) {
			e.printStackTrace();
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
