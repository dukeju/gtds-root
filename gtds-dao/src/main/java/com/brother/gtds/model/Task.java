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
	private Date publishDate;
	//课题类型
	//1=工程设计， 2=理论研究， 3= 实验研究， 4=计算机软件研制， 5=综合，6=经管文， 7=法学， 8=艺术
	private Integer type;
	//课题来源
	//科研项目 (1=国家级， 2=部级， 3=省级， 4=厅级， 5=市级， 6=校级），7=企业项目， 8=生产实际， 9=实际应用， 10=自选
	private Integer source;
	//面向专业
	private Major major;
	//是否通过审批，默认为true
	private boolean pass = true;
	//课题容量
	private Integer capacity;
	//课题由指导老师来定
	private Teacher tutor;

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
	
	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}


	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
}
