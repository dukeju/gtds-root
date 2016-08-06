package com.brother.gtds.service;

import com.brother.gtds.model.Department;

public interface DepartmentService extends BaseService<Department> {

	//返回是否超过截止出题日期
	boolean beyondProposeExpiry(String departmentId);

	//返回是否早于学生选题时间
	boolean beforeSelectBegin(String dId);

	//返回是否超过学生选题日期
	boolean beyondSelectEnd(String dId);

}
