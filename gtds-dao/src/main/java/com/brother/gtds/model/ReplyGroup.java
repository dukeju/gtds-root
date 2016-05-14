package com.brother.gtds.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//答辩小组
public class ReplyGroup implements Serializable {

	private static final long serialVersionUID = 8554048819969682322L;

	private String id;
	//答辩时间，由领导安排
	private String time;
	//答辩地点，由领导安排
	private String place;
	//是否公开答辩
	private boolean pub = false;
	//指导老师，课题由该老师提出
	private Teacher advisor;
	//评阅老师
	private Teacher valuator;
	//答辩小组组长
	private Teacher head;
	//答辩记录人
	private Teacher recorder;
	//导师，三个或以上的相似研究方向的导师，由领导安排
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
	public Teacher getAdvisor() {
		return advisor;
	}
	public void setAdvisor(Teacher advisor) {
		this.advisor = advisor;
	}
	public Teacher getValuator() {
		return valuator;
	}
	public void setValuator(Teacher valuator) {
		this.valuator = valuator;
	}
	public Teacher getHead() {
		return head;
	}
	public void setHead(Teacher head) {
		this.head = head;
	}
	public Teacher getRecorder() {
		return recorder;
	}
	public void setRecorder(Teacher recorder) {
		this.recorder = recorder;
	}
	public Set<Teacher> getTutors() {
		return tutors;
	}
	public void setTutors(Set<Teacher> tutors) {
		this.tutors = tutors;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
