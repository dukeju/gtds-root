package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Student;

public interface StudentService extends BaseService<Student> {

	//根据查询条件找出学生
	List<Student> findByQuery(String idQuery, String nameQuery,
			String majorQuery, String departmentQuery, String tutorQuery,
			String taskQuery);

}
