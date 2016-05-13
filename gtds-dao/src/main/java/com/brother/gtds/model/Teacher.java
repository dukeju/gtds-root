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
	private Set<Task> tasks = new HashSet<Task>();
	//答辩小组
	private Set<ReplyGroup> groups = new HashSet<ReplyGroup>();
	
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
	public Set<ReplyGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<ReplyGroup> groups) {
		this.groups = groups;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
