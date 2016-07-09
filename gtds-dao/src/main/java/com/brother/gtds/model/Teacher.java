package com.brother.gtds.model;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

	private static final long serialVersionUID = -5950952907776022353L;

	// 职称
	// 1=助教，2=讲师，3=副教授，4=教授
	private Integer position;
	// 研究方向，待定
	private Integer research;
	//指导类别，0表示本科，1表示研究生，2表示硕士，3表示博士
	private Integer type;
	//指导人数不得大于6人（含6人）
	private Integer count;
	// 课题，一般申请 1-3 个课题
	private Set<Task> tasks = new HashSet<Task>();
	// 答辩小组
	private Set<AnswerGroup> groups = new HashSet<AnswerGroup>();

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getResearch() {
		return research;
	}

	public void setResearch(Integer research) {
		this.research = research;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<AnswerGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<AnswerGroup> groups) {
		this.groups = groups;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
