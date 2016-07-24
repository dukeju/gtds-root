package com.brother.gtds.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.model.Department;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.DepartmentService;
import com.brother.gtds.service.TeacherService;

@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction<Teacher> {

	private static final long serialVersionUID = -7248122832460433015L;
	
	@Resource
	private TeacherService teacherService;
	@Resource
	private DepartmentService departmentService;
	
	private String teacherId;
	private Integer teacherType;
	private Integer teacherCount;
	
	//查询条件
	private String idQuery;
	private String nameQuery;
	private String positionQuery;
	private String typeQuery;
	//department的id
	private String departmentQuery;
	//装载符合条件的Teacher的List
	private List<Teacher> teachers;
	private List<Department> departments;
	
	//自己本届出的课题
	private List<Task> myTasks;
	
	//显示符合条件的教师
	public String showTeachers()
	{
		Department allDept = new Department("00", "<————全部————>");
		this.departments = this.departmentService.findAllEntities();
		this.departments.add(0, allDept);
		
		teachers = teacherService.findByQuery(idQuery, nameQuery, departmentQuery, positionQuery, typeQuery); 
		return "teacherListPage";
	}
	
	//老师的详细信息
	public String getDetailInfo()
	{
		model = teacherService.getTeacherInfo(teacherId);
		return "teacherInfoPage";
	}
	
	//编辑指导类型和数目
	public String editTypeAndCount()
	{
		model = teacherService.getEntity(teacherId);
		//如果count为null，则文本框为默认值6
		if(model.getCount() == null)
			teacherCount = 6;
		else
			teacherCount = model.getCount();
		return "editTypeAndCountPage";
	}
	
	//更新指导类型和数目
	public String updateTypeAndCount()
	{
		this.teacherService.updateTypeAndCount(teacherId, teacherType, teacherCount);
		return "teacherInfoAction";
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getIdQuery() {
		return idQuery;
	}

	public void setIdQuery(String idQuery) {
		this.idQuery = idQuery;
	}

	public String getNameQuery() {
		return nameQuery;
	}

	public void setNameQuery(String nameQuery) {
		this.nameQuery = nameQuery;
	}

	public String getPositionQuery() {
		return positionQuery;
	}

	public void setPositionQuery(String positionQuery) {
		this.positionQuery = positionQuery;
	}

	public String getTypeQuery() {
		return typeQuery;
	}

	public void setTypeQuery(String typeQuery) {
		this.typeQuery = typeQuery;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String getDepartmentQuery() {
		return departmentQuery;
	}

	public void setDepartmentQuery(String departmentQuery) {
		this.departmentQuery = departmentQuery;
	}

	public List<Task> getMyTasks() {
		return myTasks;
	}

	public void setMyTasks(List<Task> myTasks) {
		this.myTasks = myTasks;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}

	public Integer getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(Integer teacherCount) {
		this.teacherCount = teacherCount;
	}

}
