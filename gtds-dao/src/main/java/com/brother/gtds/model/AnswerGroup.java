package com.brother.gtds.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//答辩小组
public class AnswerGroup implements Serializable {

	private static final long serialVersionUID = 8554048819969682322L;

	private Integer id;
	// 答辩时间，由领导安排
	private String time;
	// 答辩地点，由领导安排
	private String place;
	// 是否公开答辩
	private boolean pub = false;
	// 指导老师，课题由该老师提出
	private Teacher tutor;
	// 评阅老师，不能是指导老师
	private Teacher insepector;
	// 答辩小组组长
	private Teacher head;
	// 答辩记录人名字
	private String recorder;
	// 导师，三个或以上的相似研究方向的导师，由领导安排
	private Set<Teacher> tutors = new HashSet<Teacher>();

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	public Teacher getInsepector() {
		return insepector;
	}

	public void setInsepector(Teacher insepector) {
		this.insepector = insepector;
	}

	public Teacher getHead() {
		return head;
	}

	public void setHead(Teacher head) {
		this.head = head;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public Set<Teacher> getTutors() {
		return tutors;
	}

	public void setTutors(Set<Teacher> tutors) {
		this.tutors = tutors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
