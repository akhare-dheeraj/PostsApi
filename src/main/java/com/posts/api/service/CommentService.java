package com.posts.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.posts.api.beans.Comment;
import com.posts.api.dao.AbstractBaseDao;

public class CommentService {

	@Autowired
	private AbstractBaseDao<Comment> commentDao;

	public void persistComment(Comment comment) {
		try {
			commentDao.openSessionAndTransaction();
			commentDao.persist(comment);
		} catch (Exception e) {
			e.printStackTrace();
			commentDao.rollBackAndCloseSession();
			throw e;
		} finally {
			commentDao.commitTransactionAndCloseSession();
		}
	}

	public void updateComment(Comment comment) {
		try {
			commentDao.openSessionAndTransaction();
			commentDao.update(comment);
		} catch (Exception e) {
			e.printStackTrace();
			commentDao.rollBackAndCloseSession();
			throw e;
		} finally {
			commentDao.commitTransactionAndCloseSession();
		}
	}

	public void deleteComment(Long id) {
		try {
			commentDao.openSessionAndTransaction();
			commentDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			commentDao.rollBackAndCloseSession();
			throw e;
		} finally {
			commentDao.commitTransactionAndCloseSession();
		}
	}

	public Comment getComment(Long id) {
		try {
			commentDao.openSessionAndTransaction();
			return commentDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			commentDao.rollBackAndCloseSession();
			throw e;
		} finally {
			commentDao.commitTransactionAndCloseSession();
		}
	}

	public List<Comment> getAllComments() {
		try {
			commentDao.openSessionAndTransaction();
			return commentDao.getAllBeans();
		} catch (Exception e) {
			e.printStackTrace();
			commentDao.rollBackAndCloseSession();
			throw e;
		} finally {
			commentDao.commitTransactionAndCloseSession();
		}
	}
}
