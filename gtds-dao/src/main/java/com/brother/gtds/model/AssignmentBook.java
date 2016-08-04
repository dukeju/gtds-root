package com.brother.gtds.model;

import java.io.Serializable;

//任务书
public class AssignmentBook implements Serializable {

	private static final long serialVersionUID = -5166665776176895068L;

	// 跟学号一样
	private String sid;
	// 论文题目
	private String title;
	// 保存路径
	private String path;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
