package com.brother.gtds.service;

import com.brother.gtds.model.Department;

public interface DepartmentService extends BaseService<Department> {

	//返回是否超过截止出题日期
	boolean beyondExpiryDate(String departmentId);

}
