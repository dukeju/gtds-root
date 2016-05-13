package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//论文
public class Thesis implements Serializable{

	private static final long serialVersionUID = 902349944245940853L;

	private String id;
	private String title;
	//论文说明
	private String explain;
	private Date date;
	private Student student;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
