package com.brother.gtds.service;

import com.brother.gtds.model.Department;

public interface DepartmentService extends BaseService<Department> {

	//返回是否早于老师拟题的开始日期
	boolean beforeProposeBegin(String dId);
	
	//返回是否超过截止出题日期
	boolean beyondProposeExpiry(String departmentId);

	//返回是否早于学生选题开始时间
	boolean beforeSelectBegin(String dId);

	//返回是否超过学生选题截止日期
	boolean beyondSelectEnd(String dId);

	//返回是否超过学生自拟课题的截止日期(比开始选题早一天)
	boolean beyondStuProposeExpiry(String dId);
	
	//返回是否早于学生自拟课题的开始日期
	boolean beforeStuProposeBegin(String dId);
	
}
