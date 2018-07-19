package com.posts.api.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.posts.api.beans.BaseBean;

@Transactional
public class BaseDaoImpl implements BaseDao {

	protected static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Serializable save(BaseBean bean) {
		try {
			logger.debug("Saving bean to databse: " + bean);
			Session session = getSession();
			Serializable id = session.save(bean);
			logger.debug("Saved bean to database: " + bean);
			session.close();
			return id;
		} catch (Exception e) {
			logger.error("Error occured while saving to database: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Serializable id, Class<? extends BaseBean> className) {
		try {
			logger.debug("Trying to delete the bean with id: "+ id);
			Session session = getSession();
			BaseBean bean = (BaseBean) session.get(className, id);
			session.delete(bean);
			logger.debug("Bean deleted.");
			session.close();
			return true;
		} catch (Exception e) {
			logger.error("Error occurred while deleting the bean: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BaseBean update(BaseBean bean) {
		try {
			logger.debug("Updating bean :"+bean);
			Session session = getSession();
			session.saveOrUpdate(bean);
			session.close();
			logger.debug("Bean updated successfully: "+bean);
			return bean;
		} catch(Exception e) {
			logger.error("Error occurred while updating the bean:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BaseBean get(Serializable id, Class<? extends BaseBean> className) {
		try {
			logger.debug("Fetching bean by ID:"+id+" Table:"+className);
			Session session = getSession();
			BaseBean bean = (BaseBean) session.get(className, id);
			session.close();
			logger.debug("Fetched bean:"+bean);
			return bean;
		} catch(Exception e) {
			logger.error("Failed while fetching bean:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<BaseBean> getAllBeans(Class<? extends BaseBean> className) {
		Set<BaseBean> beans = new HashSet<BaseBean>();
		try {
			logger.debug("Fetching all beans for :"+className);
			Session session = getSession();
			beans = new HashSet<BaseBean>(session.createCriteria(className).list());
			return beans;
		} catch(Exception e) {
			logger.error("Failed to fetch: "+e.getMessage());
			e.printStackTrace();
		}
		return beans;
	}

	private Session getSession() {
		return sessionFactory.openSession();
	}

}
