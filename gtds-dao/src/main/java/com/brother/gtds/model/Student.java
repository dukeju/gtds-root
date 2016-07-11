package com.brother.gtds.model;

public class Student extends User {

	private static final long serialVersionUID = -1211650641143182682L;

	//班级
	private String cla;
	//专业
	private Major major;
	//论文
	private Thesis thesis;
	//课题
	private Task task;
	//指导老师
	private Teacher tutor;
	//评阅老师，不能是指导老师，副教授及副教授以上
	private Teacher inspector;
	//答辩小组
	private AnswerGroup group;
	
	public Teacher getInspector() {
		return inspector;
	}

	public void setInspector(Teacher inspector) {
		this.inspector = inspector;
	}

	public AnswerGroup getGroup() {
		return group;
	}

	public void setGroup(AnswerGroup group) {
		this.group = group;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
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

	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password="
				+ password + ", email=" + email + ", phone=" + phone
				+ ", department=" + department + ", cla=" + cla + ", major="
				+ major + ", thesis=" + thesis + ", task=" + task + ", tutor="
				+ tutor + ", inspector=" + inspector + ", group=" + group + "]";
	}

}
