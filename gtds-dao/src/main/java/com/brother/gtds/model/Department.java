package com.brother.gtds.model;

import java.io.Serializable;

//学院
public class Department implements Serializable {

	private static final long serialVersionUID = -6713645149281432661L;

	private String id;
	private String name;

	public Department() {
		super();
	}

	public Department(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
