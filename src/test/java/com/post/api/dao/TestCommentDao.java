package com.post.api.dao;

import java.io.Serializable;
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
import com.posts.api.dao.AbstractBaseDao;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCommentDao {
	
	@Autowired
	private AbstractBaseDao<User> userDao;
	
	@Autowired
	private AbstractBaseDao<Comment> commentDao;
	
	@Test
	public void testSaveComment() {
		User user = new User("User", 24, 'M');
		Post post = new Post("Title", "Topic", "Description", user);
		user.getPosts().add(post);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		Comment comment = new Comment("Description", user, post);
		user.getComments().add(comment);
		post.getComments().add(comment);
		commentDao.openSessionAndTransaction();
		commentDao.persist(comment);
		commentDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(comment.getCommentId());
	}
	
	@Test
	public void testGetAllBeans() {
		User user = new User("User", 24, 'M');
		Post post = new Post("Title", "Topic", "Description", user);
		Comment c1 = new Comment("Description", user, post);
		Comment c2 = new Comment("Description 1", user, post);
		user.getComments().add(c1);
		user.getComments().add(c2);
		post.getComments().add(c1);
		post.getComments().add(c2);
		user.getPosts().add(post);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		commentDao.openSessionAndTransaction();
		List<Comment> comments = commentDao.getAllBeans();
		commentDao.commitTransactionAndCloseSession();
		Assert.assertTrue(comments.size()>=2);
	}
	
	@Test
	public void testDelete() {
		User user = new User("User1", 24, 'M');
		Post post = new Post("Title", "Topic", "Description", user);
		Comment comment = new Comment("Description", user, post);
		
		user.getPosts().add(post);
		post.getComments().add(comment);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
		Serializable id = comment.getCommentId();
		commentDao.openSessionAndTransaction();
		commentDao.delete(comment.getCommentId());
		commentDao.commitTransactionAndCloseSession();
		commentDao.openSessionAndTransaction();
		Comment cmt = commentDao.get(id);
		commentDao.commitTransactionAndCloseSession();
		Assert.assertNull(cmt);
	}
}
