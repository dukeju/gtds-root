package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//课题
public class Task implements Serializable {

	private static final long serialVersionUID = 6235557918956045420L;

	private Integer id;
	//课题名称
	private String name;
	//课题要求
	private String demand;
	//发布时间
	private Date publish;
	//课题类型
	private String type;
	//面向专业
	private String major;
	//课题简介
	private String introduction;
	//课题容量
	private Integer capacity;
	//课题由答辩小组导师组长来定
	private Teacher tutor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public Date getPublish() {
		return publish;
	}

	public void setPublish(Date publish) {
		this.publish = publish;
	}

	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
}
