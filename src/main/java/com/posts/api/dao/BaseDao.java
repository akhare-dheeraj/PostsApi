package com.posts.api.dao;

import java.io.Serializable;
import java.util.Set;

import com.posts.api.beans.BaseBean;

public interface BaseDao {
	public Serializable save(BaseBean bean);
	public boolean delete(Serializable id, Class<? extends BaseBean> className);
	public BaseBean update(BaseBean bean);
	public BaseBean get(Serializable id, Class<? extends BaseBean> className);
	public Set<BaseBean> getAllBeans(Class<? extends BaseBean> className);
}
