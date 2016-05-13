package com.brother.gtds.model;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

	private static final long serialVersionUID = -5950952907776022353L;

	//职称
	private String position;
	//研究方向
	private String research;
	//课题，可以申请 1-3 个课题
	private Set<Task> task = new HashSet<Task>();
	//答辩小组
	private ReplyGroup group;
	//指导学生
	private Set<Student> students = new HashSet<Student>();
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getResearch() {
		return research;
	}
	public void setResearch(String research) {
		this.research = research;
	}
	public ReplyGroup getGroup() {
		return group;
	}
	public void setGroup(ReplyGroup group) {
		this.group = group;
	}
	public Set<Task> getTask() {
		return task;
	}
	public void setTask(Set<Task> task) {
		this.task = task;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
