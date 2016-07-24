package com.brother.gtds.service.impl;

import java.util.Date;

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

	//返回是否超过截止出题日期
	@Override
	public boolean beyondExpiryDate(String departmentId) {
		Department d = this.getEntity(departmentId);
		//如果超过了截止日期
		if(d.getExpiryDate().before(new Date()))
			return true;
		else
			return false;
	}

}
