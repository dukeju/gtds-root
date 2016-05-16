package com.brother.gtds.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.service.BaseService;

/**
 *基本的业务逻辑类
 *专门用于继承 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;
	
	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	public void BatchEntityByHQL(String hql, Object... objects) {
		baseDao.BatchEntityByHQL(hql, objects);
	}

	public void executeSql(String sql, Object... objects) {
		baseDao.executeSql(sql, objects);
	}

	public T loadEntity(Integer id) {
		return baseDao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return baseDao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return baseDao.findEntityByHQL(hql, objects);
	}

	public Object uniqueResult(String hql, Object... objects) {
		return baseDao.uniqueResult(hql, objects);
	}

	public List<T> executeSQLQuery(Class<?> clazz, String sql,
			Object... objects) {
		return baseDao.executeSQLQuery(clazz, sql, objects);
	}

}
