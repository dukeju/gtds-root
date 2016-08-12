package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//学院
/**
 *	日期先后条
 *	proposeBegin proposeExpiry stuProposeBegin selectBegin selectEnd
 */
public class Department implements Serializable {

	private static final long serialVersionUID = -6713645149281432661L;

	private String id;
	private String name;
	//老师拟定课题的开始日期
	private Date proposeBegin;
	//拟定课题的截止日期
	private Date proposeExpiry;
	//学生自拟课题的开始日期（截止时间为选择课题的前一天）
	private Date stuProposeBegin;
	//学生选择课题的开始日期
	private Date selectBegin;
	//学生选题的截止日期
	private Date selectEnd;

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

	public Date getProposeExpiry() {
		return proposeExpiry;
	}

	public void setProposeExpiry(Date proposeExpiry) {
		this.proposeExpiry = proposeExpiry;
	}

	public Date getSelectBegin() {
		return selectBegin;
	}

	public Date getSelectEnd() {
		return selectEnd;
	}

	public void setSelectBegin(Date selectBegin) {
		this.selectBegin = selectBegin;
	}

	public void setSelectEnd(Date selectEnd) {
		this.selectEnd = selectEnd;
	}

	public Date getProposeBegin() {
		return proposeBegin;
	}

	public void setProposeBegin(Date proposeBegin) {
		this.proposeBegin = proposeBegin;
	}

	/**
	 * @return the stuProposeBegin
	 */
	public Date getStuProposeBegin() {
		return stuProposeBegin;
	}

	/**
	 * @param stuProposeBegin the stuProposeBegin to set
	 */
	public void setStuProposeBegin(Date stuProposeBegin) {
		this.stuProposeBegin = stuProposeBegin;
	}

}
