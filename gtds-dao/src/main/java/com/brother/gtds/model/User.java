package com.brother.gtds.model;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 3943262365995878180L;

	//学号或工号
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
