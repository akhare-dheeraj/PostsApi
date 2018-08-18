package com.posts.api.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.posts.api.beans.Post;

public class PostDao extends AbstractBaseDao<Post> {
	protected static final Logger logger = LoggerFactory.getLogger(PostDao.class);
	@Override
	public Post get(Serializable id) {
		logger.info("Fetching post with id:"+id);
		Session session = getCurrentSession();
		Post post = (Post) session.get(Post.class, id);
		logger.info("Fetched comment:"+post);
		return post;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllBeans() {
		logger.info("Fetching all beans for post");
		Session session = getCurrentSession();
		List<Post> list = session.createCriteria(Post.class).list();
		logger.info("Fetched posts list:"+list);
		return list;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting comment with id:"+id);
		Session session = getCurrentSession();
		Post post = (Post) session.get(Post.class, id);
		if(post!=null)
			session.delete(post);
		logger.info("Bean deleted with id:"+id);
	}

}
