package com.brother.gtds.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Student;
import com.brother.gtds.service.StudentService;

@Controller
public class StudentAction extends BaseAction<Student> {

	private static final long serialVersionUID = 7919543107393901986L;

	@Resource
	private StudentService studentService;
	
	//查询条件
	private String idQuery;
	private String nameQuery;
	private String majorQuery;
	private String departmentQuery;
	private String tutorQuery;
	private String taskQuery;
	//装载符合条件的Student的List
	private List<Student> students;
	
	//显示符合条件的学生信息
	public String showStudents()
	{
		this.students = studentService.findByQuery(idQuery, nameQuery, majorQuery, departmentQuery, tutorQuery, taskQuery);
		return "studentListPage";
	}

	public String getIdQuery() {
		return idQuery;
	}

	public void setIdQuery(String idQuery) {
		this.idQuery = idQuery;
	}

	public String getNameQuery() {
		return nameQuery;
	}

	public void setNameQuery(String nameQuery) {
		this.nameQuery = nameQuery;
	}

	public String getMajorQuery() {
		return majorQuery;
	}

	public void setMajorQuery(String majorQuery) {
		this.majorQuery = majorQuery;
	}

	public String getDepartmentQuery() {
		return departmentQuery;
	}

	public void setDepartmentQuery(String departmentQuery) {
		this.departmentQuery = departmentQuery;
	}

	public String getTutorQuery() {
		return tutorQuery;
	}

	public void setTutorQuery(String tutorQuery) {
		this.tutorQuery = tutorQuery;
	}

	public String getTaskQuery() {
		return taskQuery;
	}

	public void setTaskQuery(String taskQuery) {
		this.taskQuery = taskQuery;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
