package com.brother.gtds.model;

import java.io.Serializable;

//院系
public class Department implements Serializable {

	private static final long serialVersionUID = -6713645149281432661L;

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
