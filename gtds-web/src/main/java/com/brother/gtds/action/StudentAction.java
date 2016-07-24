package com.brother.gtds.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Department;
import com.brother.gtds.model.Major;
import com.brother.gtds.model.Student;
import com.brother.gtds.service.DepartmentService;
import com.brother.gtds.service.MajorService;
import com.brother.gtds.service.StudentService;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private static final long serialVersionUID = 7919543107393901986L;

	@Resource
	private StudentService studentService;
	@Resource
	private MajorService majorService;
	@Resource
	private DepartmentService departmentService;
	
	//查询条件
	private String idQuery;
	private String nameQuery;
	//major的id
	private String majorQuery;
	//department的id
	private String departmentQuery;
	private String tutorQuery;
	private String taskQuery;
	//装载符合条件的Student的List
	private List<Student> students;
	private List<Major> majors;
	private List<Department> departments;
	
	//显示符合条件的学生信息
	public String showStudents()
	{
		Department allDept = new Department("00", "<———全部———>");
		this.departments = this.departmentService.findAllEntities();
		this.departments.add(0, allDept);
		
		Major allMajor = new Major("00", "<———————全部———————>");
		majors = this.majorService.findAllEntities();
		majors.add(0, allMajor);
		
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

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
}
