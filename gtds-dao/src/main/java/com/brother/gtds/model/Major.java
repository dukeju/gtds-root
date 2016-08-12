package com.brother.gtds.model;

import java.io.Serializable;

public class Major implements Serializable {

	private static final long serialVersionUID = -719306196528214852L;
	
	private String id;
	private String majorName;
	private Department department;

	public Major() {
		super();
	}

	public Major(String id, String majorName) {
		this.id = id;
		this.majorName = majorName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
