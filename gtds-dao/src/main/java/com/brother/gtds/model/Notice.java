package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//通知
public class Notice implements Serializable {

	private static final long serialVersionUID = 6458046036173089488L;

	private Integer id;
	// 教师发送者
	private Teacher teaSender;
	// 领导发送者
	private Admin adminSender;
	//学生发送者
	private Student stuSender;
	// 教师接收者
	private Teacher teaReceiver;
	// 学生接受者
	private Student stuReceiver;
	// 是否确认了通知
	private boolean receive = false;
	private Date date;
	private String title;
	private String message;
	// 附带文件的地址
	private String filePath;
	// 表示哪一个系的公告，为空即向所有系发送公告
	private Department dapartment;

	public Integer getId() {
		return id;
	}

	public Teacher getTeaSender() {
		return teaSender;
	}

	public Admin getAdminSender() {
		return adminSender;
	}

	public Teacher getTeaReceiver() {
		return teaReceiver;
	}

	public Student getStuReceiver() {
		return stuReceiver;
	}

	public boolean isReceive() {
		return receive;
	}

	public Date getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getFilePath() {
		return filePath;
	}

	public Department getDapartment() {
		return dapartment;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTeaSender(Teacher teaSender) {
		this.teaSender = teaSender;
	}

	public void setAdminSender(Admin adminSender) {
		this.adminSender = adminSender;
	}

	public void setTeaReceiver(Teacher teaReceiver) {
		this.teaReceiver = teaReceiver;
	}

	public void setStuReceiver(Student stuReceiver) {
		this.stuReceiver = stuReceiver;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setDapartment(Department dapartment) {
		this.dapartment = dapartment;
	}

	public Student getStuSender() {
		return stuSender;
	}

	public void setStuSender(Student stuSender) {
		this.stuSender = stuSender;
	}

}
