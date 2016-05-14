package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//公告
public class Notice implements Serializable {

	private static final long serialVersionUID = 6458046036173089488L;
	
	private Integer id;
	private Date date;
	private String title;
	private String message;
	//附带文件的地址
	private String fileURL;
	//表示哪一个系的公告，为空即向所有系发送公告
	private Department dapartment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Department getDapartment() {
		return dapartment;
	}
	public void setDapartment(Department dapartment) {
		this.dapartment = dapartment;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	
}
