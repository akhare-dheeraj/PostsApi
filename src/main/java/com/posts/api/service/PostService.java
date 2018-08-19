package com.posts.api.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.posts.api.beans.Post;
import com.posts.api.dao.PostDao;

public class PostService {

	@Autowired
	private PostDao postDao;

	public void persistPost(Post post) {
		try {
			postDao.openSessionAndTransaction();
			postDao.persist(post);
		} catch (Exception e) {
			e.printStackTrace();
			postDao.rollBackAndCloseSession();
			throw e;
		} finally {
			postDao.commitTransactionAndCloseSession();
		}
	}

	public void updatePost(Post post) {
		try {
			postDao.openSessionAndTransaction();
			postDao.update(post);
		} catch (Exception e) {
			e.printStackTrace();
			postDao.rollBackAndCloseSession();
			throw e;
		} finally {
			postDao.commitTransactionAndCloseSession();
		}
	}

	public void deletePost(Serializable id) {
		try {
			postDao.openSessionAndTransaction();
			postDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			postDao.rollBackAndCloseSession();
			throw e;
		} finally {
			postDao.commitTransactionAndCloseSession();
		}
	}

	public Post getPost(Long id) {
		try {
			postDao.openSessionAndTransaction();
			return postDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			postDao.rollBackAndCloseSession();
			throw e;
		} finally {
			postDao.commitTransactionAndCloseSession();
		}
	}
	
	public List<Post> getAllPosts() {
		try {
			postDao.openSessionAndTransaction();
			return postDao.getAllBeans();
		} catch(Exception e) {
			e.printStackTrace();
			postDao.rollBackAndCloseSession();
			throw e;
		} finally {
			postDao.commitTransactionAndCloseSession();
		}
	}
}
