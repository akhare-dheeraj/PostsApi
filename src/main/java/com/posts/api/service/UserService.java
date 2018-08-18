package com.posts.api.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.posts.api.beans.User;
import com.posts.api.dao.UserDao;

public class UserService {
	protected static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	public User persistUser(User user) {
		try {
			userDao.openSessionAndTransaction();
			userDao.persist(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			userDao.rollBackAndCloseSession();
			throw e;
		} finally {
			userDao.commitTransactionAndCloseSession();
		}
	}

	public User updateUser(User user) {
		try {
			userDao.openSessionAndTransaction();
			userDao.update(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			userDao.rollBackAndCloseSession();
			throw e;
		} finally {
			userDao.commitTransactionAndCloseSession();
		}
	}

	public void deleteUser(Long id) {
		try {
			userDao.openSessionAndTransaction();
			userDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			userDao.rollBack();
			throw e;
		} finally {
			userDao.commitTransactionAndCloseSession();
		}
	}
	
	public List<User> getAllUsers() {
		try {
			userDao.openSessionAndTransaction();
			return userDao.getAllBeans();
		} catch(Exception e) {
			e.printStackTrace();
			userDao.rollBackAndCloseSession();
			throw e;
		} finally {
			userDao.commitTransactionAndCloseSession();
		}
	}
	
	public User getUser(Serializable id) {
		try {
			userDao.openSessionAndTransaction();
			User user = userDao.get(id);
			return user;
		} catch(Exception e) {
			e.printStackTrace();
			userDao.rollBackAndCloseSession();
			throw e;
		} finally {
			userDao.commitTransactionAndCloseSession();
		}
	}
}
