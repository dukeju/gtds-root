package com.brother.gtds.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Department;
import com.brother.gtds.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements
		DepartmentService {
	
	@Resource(name="departmentDao")
	public void setDao(BaseDao<Department> dao)
	{
		super.setDao(dao);
	}
	
}
