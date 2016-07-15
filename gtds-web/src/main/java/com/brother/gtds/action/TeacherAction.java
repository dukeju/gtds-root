package com.brother.gtds.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.TeacherService;

@Controller
public class TeacherAction extends BaseAction<Teacher>{

	private static final long serialVersionUID = -7248122832460433015L;
	
	@Resource
	private TeacherService teacherService;
	
	//查询条件
	private String idQuery;
	private String nameQuery;
	private String positionQuery;
	private String typeQuery;
	//装载符合条件的Teacher的List
	private List<Teacher> teachers;
	
	//显示符合条件的教师
	public String showTeachers()
	{
		teachers = teacherService.findByQuery(idQuery, nameQuery, positionQuery, typeQuery); 
		return "teacherListPage";
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

	public String getPositionQuery() {
		return positionQuery;
	}

	public void setPositionQuery(String positionQuery) {
		this.positionQuery = positionQuery;
	}

	public String getTypeQuery() {
		return typeQuery;
	}

	public void setTypeQuery(String typeQuery) {
		this.typeQuery = typeQuery;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}
