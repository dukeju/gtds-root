package com.brother.gtds.model;

public class Student extends User {

	private static final long serialVersionUID = -1211650641143182682L;

	//班级
	private String cla;
	//专业
	private Major major;

	public String getCla() {
		return cla;
	}

	public Major getMajor() {
		return major;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

}
