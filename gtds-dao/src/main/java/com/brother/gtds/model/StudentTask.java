package com.brother.gtds.model;

import java.io.Serializable;

/**
 * 学生选题类
 */
public class StudentTask implements Serializable {

	private static final long serialVersionUID = 2585014671240381001L;

	private Integer id;
	private Student student;
	private Task task;
	// 是否通过
	private boolean pass = false;
	
	//论文
	private Thesis thesis;
	//指导老师
	private Teacher tutor;
	//评阅老师，不能是指导老师，副教授及副教授以上
	private Teacher inspector;

	public Student getStudent() {
		return student;
	}

	public Task getTask() {
		return task;
	}

	public boolean isPass() {
		return pass;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public Teacher getTutor() {
		return tutor;
	}

	public Teacher getInspector() {
		return inspector;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	public void setInspector(Teacher inspector) {
		this.inspector = inspector;
	}

}
