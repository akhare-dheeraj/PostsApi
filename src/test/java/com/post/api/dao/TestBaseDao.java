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
}
