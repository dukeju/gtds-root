package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Teacher;

public interface TeacherService extends BaseService<Teacher> {

	//查找出符合条件的Teacher
	List<Teacher> findByQuery(String idQuery, String nameQuery, String departmentQuery, 
			String positionQuery, String typeQuery);

	//获得老师的详细信息
	Teacher getTeacherInfo(String id);

	//更新指导类型和数目
	void updateTypeAndCount(String id, Teacher model);

}
