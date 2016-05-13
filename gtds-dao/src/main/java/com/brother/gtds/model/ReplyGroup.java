package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//答辩小组
public class ReplyGroup implements Serializable {

	private static final long serialVersionUID = 8554048819969682322L;

	private Integer id;
	//小组名称
	private String name;
	//答辩信息
	private String message;
	//答辩时间，由领导安排
	private Date date;
	//答辩地点，由领导安排
	private String palce;
	//是否公开答辩
	private boolean pub;
	
	//课题
	private Task task;
	//导师，三个或以上的相似研究方向的导师
	private Set<Teacher> tutors = new HashSet<Teacher>();
	//成员
	private Set<Student> members = new HashSet<Student>();
	

}
