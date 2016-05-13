package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//论文
public class Thesis implements Serializable{

	private static final long serialVersionUID = 902349944245940853L;

	private Integer id;
	private String title;
	//论文说明
	private String explain;
	private Date date;
	private String grade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
