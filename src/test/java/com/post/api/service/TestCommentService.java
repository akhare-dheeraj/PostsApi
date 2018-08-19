package com.post.api.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.posts.api.beans.Comment;
import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.service.CommentService;
import com.posts.api.service.UserService;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCommentService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Test
	public void testPersistComment() {
		User user = new User("User-1", 23, 'M');
		Post post = new Post("Title 1", "Topic 1", "Description 1", user);
		user.getPosts().add(post);
		userService.persistUser(user);
		Comment comment = new Comment("Description 1", user, post);
		commentService.persistComment(comment);
		Assert.assertNotNull(comment.getCommentId());
	}
	
	@Test
	public void testUpdateComment() {
		User user = new User("User-1", 23, 'M');
		Post post = new Post("Title 1", "Topic 1", "Description 1", user);
		user.getPosts().add(post);
		Comment comment = new Comment("Description 1", user, post);
		user.getComments().add(comment);
		post.getComments().add(comment);
		userService.persistUser(user);
		comment.setDescription("Comment Description");
		commentService.updateComment(comment);
		Assert.assertEquals(comment.getDescription(), "Comment Description");
	}
	
	@Test
	public void testDeleteComment() {
		User user = new User("User-1", 23, 'M');
		Post post = new Post("Title 1", "Topic 1", "Description 1", user);
		user.getPosts().add(post);
		Comment comment = new Comment("Description 1", user, post);
		user.getComments().add(comment);
		post.getComments().add(comment);
		userService.persistUser(user);
		commentService.deleteComment(comment.getCommentId());
		Comment cmt = commentService.getComment(comment.getCommentId());
		Assert.assertNull(cmt);
	}
	
	@Test
	public void testGetComment() {
		User user = new User("User-1", 23, 'M');
		Post post = new Post("Title 1", "Topic 1", "Description 1", user);
		user.getPosts().add(post);
		Comment comment = new Comment("Description 1", user, post);
		user.getComments().add(comment);
		post.getComments().add(comment);
		userService.persistUser(user);
		Comment cmt = commentService.getComment(comment.getCommentId());
		Assert.assertNotNull(cmt);
	}
	
	@Test
	public void testGetAllComments() {
		User user = new User("User-1", 23, 'M');
		Post post = new Post("Title 1", "Topic 1", "Description 1", user);
		user.getPosts().add(post);
		Comment c1 = new Comment("Description 1", user, post);
		Comment c2 = new Comment("Description 2", user, post);
		user.getComments().add(c1);
		post.getComments().add(c1);
		user.getComments().add(c2);
		post.getComments().add(c2);
		userService.persistUser(user);
		List<Comment> comments = commentService.getAllComments();
		Assert.assertTrue(comments!=null && comments.size()>=2);
	}
}
