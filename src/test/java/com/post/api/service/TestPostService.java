package com.post.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.service.PostService;
import com.posts.api.service.UserService;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPostService {
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testPersistPost() {
		User user = new User("Dheeraj", 34, 'M');
		userService.persistUser(user);
		Post post = new Post("Title", "Topic", "Description", user);
		postService.persistPost(post);
		Assert.assertNotNull(post.getPostId());
	}
	
	@Test
	public void testUpdatePost() {
		User user = new User("User-1", 24, 'F');
		Post post = new Post("Title", "Topic", "Description", user);
		user.getPosts().add(post);
		userService.persistUser(user);
		post.setTitle("Title 1");
		postService.updatePost(post);
		Assert.assertEquals("Title 1", post.getTitle());
	}
	
	@Test
	public void testDeletePost() {
		User user = new User("User-1", 24, 'F');
		Post post = new Post("Title", "Topic", "Description", user);
		user.getPosts().add(post);
		userService.persistUser(user);
		postService.deletePost(post.getPostId());
		Post deletedPost = postService.getPost(post.getPostId());
		Assert.assertNull(deletedPost);
	}
	
	@Test
	public void testGetPost() {
		User user = new User("User-1", 24, 'F');
		Post post = new Post("Title", "Topic", "Description", user);
		user.getPosts().add(post);
		userService.persistUser(user);
		Post newPost = postService.getPost(post.getPostId());
		Assert.assertNotNull(newPost);
	}
	
	@Test
	public void testGetAllPost() {
		User user = new User("User-1", 24, 'F');
		Post p1 = new Post("Title", "Topic", "Description", user);
		Post p2 = new Post("Title", "Topic", "Description", user);
		user.getPosts().add(p1);
		user.getPosts().add(p2);
		userService.persistUser(user);
		List<Post> allPosts = postService.getAllPosts();
		Assert.assertTrue(allPosts!=null && allPosts.size()>=2);
	}
}
