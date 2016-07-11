package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Teacher;

public interface TeacherService extends BaseService<Teacher> {

	//查找出符合条件的Teacher
	List<Teacher> findByQuery(String idQuery, String nameQuery,
			String positionQuery, String typeQuery);

	//更新教师的指导类型
	void updateType(String id, Integer type);

}
