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
public class TestUserDao {
	
	@Autowired
	private AbstractBaseDao<User> userDao;
	
	@Test
	public void testPersist() {
		User user = new User("User 1", 27, 'M');
		Post post = new Post("Demo", "Religion", "Religion is good followers are not.", user);
		Comment comment = new Comment("You are right", user, post);
		user.getPosts().add(post);
		user.getComments().add(comment);
		post.getComments().add(comment);
		userDao.openSessionAndTransaction();
		userDao.persist(user);
		userDao.commitTransactionAndCloseSession();
	}
	
	@Test
	public void testSave() {
		User user = new User("User 2", 27, 'M');
		userDao.openSessionAndTransaction();
		Serializable id = userDao.save(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(id);
	}
	
	@Test
	public void testUpdate() {
		User user = new User("User 3", 27, 'M');
		userDao.openSessionAndTransaction();
		Serializable id = userDao.save(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(id);
		user.setUserName("User 4");
		userDao.openSessionAndTransaction();
		userDao.update(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertEquals(user.getUserName(), "User 4");
		Assert.assertEquals(user.getUserId(), id);
	}
	
	@Test
	public void testGet() {
		User user = new User("User 5", 28, 'M');
		userDao.openSessionAndTransaction();
		Serializable id = userDao.save(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(id);
		userDao.openSessionAndTransaction();
		User newUser = userDao.get(id);
		userDao.commitTransactionAndCloseSession();
		Assert.assertTrue(newUser.equals(user));
	}
	
	@Test
	public void testGetAllBeans() {
		User u1 = new User("User 6", 28, 'M');
		User u2 = new User("User 7", 27, 'F');
		userDao.openSessionAndTransaction();
		userDao.save(u1);
		userDao.save(u2);
		userDao.commitTransactionAndCloseSession();
		userDao.openSessionAndTransaction();
		List<User> userList = userDao.getAllBeans();
		userDao.commitTransactionAndCloseSession();
		Assert.assertTrue(userList.size()>=2);
	}
	
	@Test
	public void testDelete() {
		User user = new User("User 8", 28, 'M');
		userDao.openSessionAndTransaction();
		Serializable id = userDao.save(user);
		userDao.commitTransactionAndCloseSession();
		Assert.assertNotNull(id);
		userDao.openSessionAndTransaction();
		userDao.delete(id);
		userDao.commitTransactionAndCloseSession();
	}
}
