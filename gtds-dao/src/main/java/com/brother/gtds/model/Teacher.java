package com.brother.gtds.model;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

	private static final long serialVersionUID = -5950952907776022353L;

	// 职称
	// 0=助教，1=讲师，2=副教授，3=教授
	private Integer position;
	// 研究方向，待定
	private String research;
	//指导类别，0表示专科，1表示本科，2表示研究生，3表示硕士，4表示博士
	private Integer type;
	//指导人数
	private Integer minCount;
	private Integer maxCount;
	// 课题，一般申请 1-3 个课题
	private Set<Task> tasks = new HashSet<Task>();
	// 答辩小组
	private Set<AnswerGroup> groups = new HashSet<AnswerGroup>();
	
	public Teacher(String id) {
		this.id = id;
	}
	
	public Teacher() {
		super();
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
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
