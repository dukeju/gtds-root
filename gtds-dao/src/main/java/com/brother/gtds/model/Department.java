package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//院系
public class Department implements Serializable {

	private static final long serialVersionUID = -6713645149281432661L;

	private String id;
	private String name;
	//拟定课题的截止日期
	private Date proposeExpiry;
	private Date selectBegin;
	//学生选题的截止日期
	private Date selectEnd;

	public Department() {
		super();
	}

	public Department(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getProposeExpiry() {
		return proposeExpiry;
	}

	public void setProposeExpiry(Date proposeExpiry) {
		this.proposeExpiry = proposeExpiry;
	}

	public Date getSelectBegin() {
		return selectBegin;
	}

	public Date getSelectEnd() {
		return selectEnd;
	}

	public void setSelectBegin(Date selectBegin) {
		this.selectBegin = selectBegin;
	}

	public void setSelectEnd(Date selectEnd) {
		this.selectEnd = selectEnd;
	}

}
