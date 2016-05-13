package com.brother.gtds.model;

public class Student extends User {

	private static final long serialVersionUID = -1211650641143182682L;

	//班级
	private String cla;
	//专业
	private String major;
	//论文
	private Thesis thesis;
	//答辩小组
	private ReplyGroup group;
	//课题
	private Task task;

	public ReplyGroup getGroup() {
		return group;
	}

	public void setGroup(ReplyGroup group) {
		this.group = group;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
