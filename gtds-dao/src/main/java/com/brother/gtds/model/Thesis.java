package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//论文
public class Thesis implements Serializable{

	private static final long serialVersionUID = 902349944245940853L;

	/*
	 * 论文编号格式
	 * 	例如：2012000001
	 * 		2012：年份
	 * 		0：学位，0表示本科，1表示研究生，2表示硕士，3表示博士
	 * 		00001：按顺序编号
	 */
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
