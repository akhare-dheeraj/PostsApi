package com.post.api.dao;

import java.io.Serializable;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.posts.api.beans.Post;
import com.posts.api.beans.User;
import com.posts.api.dao.PostDao;
import com.posts.api.dao.UserDao;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPostDao {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDao postDao;

	@Test
	public void testSave() {
		User user = new User("User 1", 27, 'M');
		Post post = new Post("Posttitle", "Post Topic", "Post Description", user);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		postDao.openSessionAndTransaction();
		postDao.persist(post);
		postDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(post.getPostId());
	}
	
	@Test
	public void testGet() {
		User user = new User("User 2", 27, 'M');
		Post post = new Post("Posttitle", "Post Topic", "Post Description", user);
		user.getPosts().add(post);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(post.getPostId());
	}
	
	@Test
	public void testGetAllBeans() {
		User user = new User("User 3", 27, 'M');
		Post post = new Post("Posttitle", "Post Topic", "Post Description", user);
		Post post1 = new Post("Posttitle 1", "Post Topic 1", "Post Description 1", user);
		user.getPosts().add(post);
		user.getPosts().add(post1);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		postDao.openSessionAndTransaction();
		List<Post> posts = postDao.getAllBeans();
		postDao.commitTransactionAndCloseSession();
		Assert.assertTrue(posts.size()>=2);
	}
	
	@Test
	public void testDelete() {
		User user = new User("User 4", 34, 'F');
		Post post = new Post("Title 1", "Topic 1", "Description", user);
		user.getPosts().add(post);
		
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		Serializable id = post.getPostId();
		postDao.openSessionAndTransaction();
		postDao.delete(post.getPostId());
		postDao.commitTransactionAndCloseSession();
		postDao.openSessionAndTransaction();
		Post newPost = postDao.get(id);
		postDao.commitTransactionAndCloseSession();
		Assert.assertNull(newPost);
	}
}
