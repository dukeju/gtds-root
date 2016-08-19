package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 	时间安排类
 *	日期先后顺序
 *	proposeBegin proposeExpiry stuProposeBegin selectBegin selectEnd
 */
public class Schedule implements Serializable {

	private static final long serialVersionUID = -5432921764629823679L;

	private Integer id;
	private Department department;

	// 老师拟定课题的开始日期
	private Date proposeBegin;
	// 拟定课题的截止日期
	private Date proposeExpiry;
	// 学生自拟课题的开始日期（截止时间为选择课题的前一天）
	private Date stuProposeBegin;
	// 学生选择课题的开始日期
	private Date selectBegin;
	// 学生选题的截止日期
	private Date selectEnd;

	public Integer getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}

	public Date getProposeBegin() {
		return proposeBegin;
	}

	public Date getProposeExpiry() {
		return proposeExpiry;
	}

	public Date getStuProposeBegin() {
		return stuProposeBegin;
	}

	public Date getSelectBegin() {
		return selectBegin;
	}

	public Date getSelectEnd() {
		return selectEnd;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setProposeBegin(Date proposeBegin) {
		this.proposeBegin = proposeBegin;
	}

	public void setProposeExpiry(Date proposeExpiry) {
		this.proposeExpiry = proposeExpiry;
	}

	public void setStuProposeBegin(Date stuProposeBegin) {
		this.stuProposeBegin = stuProposeBegin;
	}

	public void setSelectBegin(Date selectBegin) {
		this.selectBegin = selectBegin;
	}

	public void setSelectEnd(Date selectEnd) {
		this.selectEnd = selectEnd;
	}
}
