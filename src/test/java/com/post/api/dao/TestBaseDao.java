package com.post.api.dao;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.posts.api.beans.User;
import com.posts.api.dao.BaseDao;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBaseDao {

	@Autowired
	private BaseDao baseDao;
	
	@Test
	@Rollback(true)
	public void testSaveBean() {
		User user = new User("TestUser", 26, 'M');
		baseDao.openSessionAndTransaction();
		Serializable id = baseDao.save(user);
		baseDao.commitTransaction();
		Assert.assertNotNull(id);
	}
	
	@Test
	@Rollback(true)
	public void testGetBean() {
		User user = new User("TestUser1", 26, 'M');
		baseDao.openSessionAndTransaction();
		Serializable id = baseDao.save(user);
		User bean = (User) baseDao.get(id, User.class);
		baseDao.commitTransaction();
		Assert.assertEquals(bean, user);
		baseDao.closeSession();
	}
	
	@Test
	@Rollback(true)
	public void testDeleteBean() {
		User user = new User("TestUser2", 26, 'M');
		baseDao.openSessionAndTransaction();
		Long userId = (Long) baseDao.save(user);
		baseDao.commitTransaction();
		baseDao.startTransaction();
		baseDao.delete(user.getUserId(), User.class);
		baseDao.commitTransaction();
		baseDao.startTransaction();
		Assert.assertTrue(baseDao.get(userId, User.class)==null);
		baseDao.commitTransaction();
		baseDao.closeSession();
	}
	
	@Test
	@Rollback(true)
	public void testUpdate() {
		User user = new User("TestUser3", 26, 'M');
		baseDao.openSessionAndTransaction();
		baseDao.save(user);
		baseDao.commitTransaction();
		String oldName = user.getUserName();
		user.setUserName("New Test user");
		baseDao.startTransaction();
		baseDao.update(user);
		baseDao.commitTransaction();
		baseDao.startTransaction();
		user = (User) baseDao.get(user.getUserId(), User.class);
		baseDao.commitTransaction();
		Assert.assertNotEquals(oldName, user.getUserName());
		baseDao.closeSession();
	}
	
	@Test
	@Rollback(true)
	public void testGetAllBeans() {
		User user1 = new User("User1", 26, 'F');
		User user2 = new User("User2", 26, 'F');
		baseDao.openSessionAndTransaction();
		baseDao.save(user2);
		baseDao.save(user1);
		baseDao.commitTransaction();
		baseDao.startTransaction();
		Assert.assertTrue(baseDao.getAllBeans(User.class).size()>1);
		baseDao.commitTransaction();
		baseDao.closeSession();
	}
}
