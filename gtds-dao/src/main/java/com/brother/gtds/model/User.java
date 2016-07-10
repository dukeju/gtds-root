package com.brother.gtds.model;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 3943262365995878180L;

	//学号或工号
	/*
	 * 学生学号格式：
	 * 	例如：201202050125
	 * 		2012：年份
	 * 		02：学院编号
	 * 		05：专业编号
	 * 		0：学位，0表示本科，1表示研究生，2表示硕士，3表示博士
	 * 		1：班级
	 * 		25：学生编号
	 * 老师工号格式：
	 * 	例如：2008020001
	 * 		2008:年份
	 * 		02：学院编号
	 * 		0001：按顺序增大的编号
	 * 管理员编号格式：
	 * 	例如：20040801
	 * 		2008:年份
	 * 		02：学院编号
	 * 		01：按顺序增大的编号
	 */
	private String id;
	private String name;
	private String password;
	private String email;
	private String phone;
	//院系
	private Department department;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
