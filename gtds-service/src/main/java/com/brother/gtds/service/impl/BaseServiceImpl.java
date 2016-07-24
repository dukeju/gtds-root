package com.brother.gtds.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.service.BaseService;
import com.brother.gtds.utils.ReflectionUtils;

/**
 *基本的业务逻辑类
 *专门用于继承 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	
	private Class<T> clazz;
	
	public BaseServiceImpl()
	{
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}
	
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void BatchEntityByHQL(String hql, Object... objects) {
		dao.BatchEntityByHQL(hql, objects);
	}

	public void executeSql(String sql, Object... objects) {
		dao.executeSql(sql, objects);
	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}
	
	public T loadEntity(String id) {
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}
	
	public T getEntity(String id) {
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	public List<T> findAllEntities()
	{
		String hql = "from " + clazz.getSimpleName();
		return this.findEntityByHQL(hql);
	}

	public Object uniqueResult(String hql, Object... objects) {
		return dao.uniqueResult(hql, objects);
	}

	public List<T> executeSQLQuery(Class<?> clazz, String sql,
			Object... objects) {
		return dao.executeSQLQuery(clazz, sql, objects);
	}

}
