package com.brother.gtds.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.action.aware.UserAware;
import com.brother.gtds.model.Department;
import com.brother.gtds.model.Major;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;
import com.brother.gtds.service.DepartmentService;
import com.brother.gtds.service.MajorService;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.service.StudentTaskService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.page.PageBean;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> implements UserAware, RequestAware {

	private static final long serialVersionUID = 7919543107393901986L;

	@Resource
	private StudentService studentService;
	@Resource
	private MajorService majorService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private TaskService taskService;
	@Resource
	private StudentTaskService studentTaskService;
	
	private Student user;
	private Map<String, Object> request;
	
	private Integer taskId;
	private int pageNum;
	private int pageSize = 5;;
	//查询条件
	private String idQuery;
	private String nameQuery;
	//major的id
	private String majorQuery;
	//department的id
	private String departmentQuery;
	private String tutorQuery;
	private String taskQuery;
	//装载符合条件的Student的List
	private List<Student> students;
	private List<Major> majors;
	private List<Department> departments;
	
	private InputStream inputStream;
	
	//显示符合条件的学生信息
	public String showStudents()
	{
		Department allDept = new Department("00", "<———全部———>");
		this.departments = this.departmentService.findAllEntities();
		this.departments.add(0, allDept);
		
		Major allMajor = new Major("00", "<———————全部———————>");
		majors = this.majorService.findAllEntities();
		majors.add(0, allMajor);
		
		this.students = studentService.findByQuery(idQuery, nameQuery, majorQuery, departmentQuery, tutorQuery, taskQuery);
		return "studentListPage";
	}
	
//	//显示所有可选的课题
//	public String showChoiceTasks()
//	{
//		PageBean<Task> taskPage = this.taskService.getChoicePage(user.getMajor().getId(), tutorQuery, user.getId(), pageNum, pageSize);
//		this.request.put("taskPage", taskPage);
//		this.request.put("selectedTask", studentTaskService.getSelectedTask(user.getId()));
//		return "choiceTasksPage";
//	}
//	
//	//返回可选择的导师
//	public List<Teacher> getChoiceTutors()
//	{
//		List<Teacher> choiceTutors = this.taskService.getChoiceTutors(user.getMajor().getId());
//		Teacher all = new Teacher();
//		all.setId("00");
//		all.setName("全部");
//		choiceTutors.add(0, all);
//		return choiceTutors;
//	}
	
	//选择课程
	public String selectTask()
	{
		try
		{
			//如果还没到选择课题时间
			if(departmentService.beforeSelectBegin(user.getDepartment().getId()))
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			//如果已经超过了选择课题时间
			else if(departmentService.beyondSelectEnd(user.getDepartment().getId()))
				inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
			//如果已经选过课题了
			else if(studentTaskService.isSelected(user))
			{
				inputStream = new ByteArrayInputStream("2".getBytes("utf-8"));
			}
			else
			{
				this.studentTaskService.selectTask(taskId, user);
				inputStream = new ByteArrayInputStream("3".getBytes("utf-8"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ajax";
	}
	
	//返回选择了该课题的人数
	public Long getSelectedCount(int taskId)
	{
		return studentTaskService.getSelectedCount(taskId);
	}
	
	//退选课题
	public String unselectTask()
	{
		try
		{
			//如果已经超过了选择课题时间
			if(departmentService.beyondSelectEnd(user.getDepartment().getId()))
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			else
			{
				this.studentTaskService.unselectTask(taskId, user.getId());
				inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ajax";
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

	public String getMajorQuery() {
		return majorQuery;
	}

	public void setMajorQuery(String majorQuery) {
		this.majorQuery = majorQuery;
	}

	public String getDepartmentQuery() {
		return departmentQuery;
	}

	public void setDepartmentQuery(String departmentQuery) {
		this.departmentQuery = departmentQuery;
	}

	public String getTutorQuery() {
		return tutorQuery;
	}

	public void setTutorQuery(String tutorQuery) {
		this.tutorQuery = tutorQuery;
	}

	public String getTaskQuery() {
		return taskQuery;
	}

	public void setTaskQuery(String taskQuery) {
		this.taskQuery = taskQuery;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public void setUser(User user) {
		this.user = (Student) user;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
