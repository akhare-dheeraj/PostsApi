package com.posts.api.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.posts.api.beans.Comment;

public class CommentDao extends AbstractBaseDao<Comment> {
	protected static final Logger logger = LoggerFactory.getLogger(CommentDao.class);
	
	@Override
	public Comment get(Serializable id) {
		logger.info("Fetching comment with id:"+id);
		Session session = getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, id);
		logger.info("Fetched comment:"+comment);
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllBeans() {
		logger.info("Fetching all beans for comment");
		Session session = getCurrentSession();
		List<Comment> list = session.createCriteria(Comment.class).list();
		logger.info("Fetched comments list:"+list);
		return list;
	}

	@Override
	public void delete(Serializable id) {
		logger.info("Deleting comment with id:"+id);
		Session session = getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, id);
		if(comment!=null)
			session.delete(comment);
		logger.info("Bean deleted with id:"+id);
	}
}
