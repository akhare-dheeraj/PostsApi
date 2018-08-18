package com.posts.api.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.posts.api.beans.User;

public class UserDao extends AbstractBaseDao<User> {

	protected static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	@Override
	public User get(Serializable id) {
		logger.info("Fetching user with id:"+id);
		Session session = getCurrentSession();
		User user = (User) session.get(User.class, id);
		logger.info("Fetched comment:"+user);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllBeans() {
		logger.info("Fetching all beans for user");
		Session session = getCurrentSession();
		List<User> list = session.createCriteria(User.class).list();
		logger.info("Fetched posts list:"+list);
		return list;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting user with id:"+id);
		Session session = getCurrentSession();
		User user = (User) session.get(User.class, id);
		if(user!=null)
			session.delete(user);
		logger.info("User deleted with id:"+id);
	}

}
