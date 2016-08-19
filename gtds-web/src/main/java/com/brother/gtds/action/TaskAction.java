package com.brother.gtds.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brother.gtds.action.aware.UserAware;
import com.brother.gtds.model.Major;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;
import com.brother.gtds.service.MajorService;
import com.brother.gtds.service.ScheduleService;
import com.brother.gtds.service.StudentTaskService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.service.page.PageBean;
import com.brother.gtds.utils.FileUtils;
import com.brother.gtds.utils.ValidationUtils;

@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task> implements UserAware, ServletContextAware, RequestAware{

	private static final long serialVersionUID = 6887834321049118028L;
	
	@Resource
	private TaskService taskService;
	@Resource
	private MajorService majorService;
	@Resource
	private ScheduleService scheduleService;
	@Resource
	private StudentTaskService studentTaskService;
	@Resource
	private TeacherService teacherService;
	
	private InputStream inputStream;
	
	//接收User
	private User user;
	
	private Integer taskId;
	private String taskName;
	private int pageNum;
	private int pageSize = 5;
	
	//导师的ID
	private String tutorQuery;
	//面向的专业
	private String majorQuery;
	//查看近几年的课题，最大值为3. 0表示本届
	private Integer year = 3;
	private List<Task> historyTasks;
	private List<Task> tasks;
	//00表示<--全部-->
	private List<Major> majors;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private ServletContext sc;
	private Map<String, Object> request;
	
	////查找所有通过审核的课题
	public String showHistoryTasks()
	{
		Major all = new Major("00", "<———————全部———————>");
		majors = this.majorService.findAllEntities();
		majors.add(0, all);
		
		historyTasks = this.taskService.findHistoryPassTasks(majorQuery, year);
		return "historyTasksListPage";
	}
	
	//到达我的课题页面（只显示本届的）
	public String myCurrentTasks()
	{
		Teacher teacher = (Teacher) user;
		request.put("minCount", teacher.getMinCount());
		request.put("maxCount", teacher.getMaxCount());
		
		tasks = taskService.getMyCurrentTasks(teacher.getId());
		return "myTaskListPage";
	}
	
	//到达我的课题页面（显示全部的）
	public String myAllTasks()
	{
		Teacher teacher = (Teacher) user;
		request.put("minCount", teacher.getMinCount());
		request.put("maxCount", teacher.getMaxCount());
		
		tasks = taskService.getMyAllTasks(teacher.getId());
		return "myTaskListPage";
	}	
	
	//修改课题
	public String editTask()
	{
		if(this.isEditable(taskId))
		{
			if(taskId != null)
				this.model = this.taskService.getEntity(taskId);
			this.majors = this.majorService.findAllEntities();
			return "editTaskPage";
		}
		else
			return "myTaskListPage";
	}
	
	//更新或者拟定课题
	public String saveOrUpdateTask() throws Exception
	{
		if(this.isEditable(model.getId()))
		{
			model.setTutor((Teacher) user);
			
//			if(file != null)
//			{
				String dir = sc.getRealPath("/upload/教师拟题审批表");
//				String extension = fileFileName.substring(fileFileName.lastIndexOf("."));
//				long l = System.nanoTime();
//				//保存文件
//				FileUtils.saveFile(file, new File(dir, l + extension));
//				//删除原来的文件
//				FileUtils.deleteFile(new File(dir, model.getPath()));
//				//保存新的文件名
//				String path = l + extension;
//				model.setPath(path);
//			}
			
			taskService.saveOrUpdateTask(model, file, dir, fileFileName);
		}
		return "myTaskListAction";
	}
	//在saveOrUpdateTask()方法执行前执行
	public void prepareSaveOrUpdateTask()
	{
		this.majors = majorService.findAllEntities();
	}
	
	//删除课题，必须在截止日期前
	public String deleteTask()
	{
		if(this.isEditable(taskId))
		{
			if(taskId != null)
			{
				Task task = taskService.getEntity(taskId);
				String path = task.getPath();
				if(ValidationUtils.validateString(path))
				{
					String dir = sc.getRealPath("/upload/教师拟题审批表");
					//删除文件
					FileUtils.deleteFile(new File(dir, path));
				}
				
				taskService.deleteEntity(task);;
			}
		}
		return "myTaskListAction";
	}
	
	//指定课题是否可编辑的
	public boolean isEditable(Integer tId)
	{
		if(taskService.isEditableTask(user.getDepartment().getId(), tId))
			return true;
		return false;
	}
	//是否可以新建课题
	public boolean haveNewTask()
	{
		return isEditable(null);
	}
	
	//检验是否有重名课题
	public String validateName() throws Exception
	{
		//如果课题名称有效
		if(taskService.validateName(taskId, taskName, (Teacher) user))
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		else
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
		return "validateNameAjax";
	}
	
	//除去指定课题外的最大课题剩余容量
	public int getRestCapacity(Integer id)
	{
		return taskService.getRestCapacity(id, (Teacher) user);
	}
	
	//是否超过导师的最小指导人数
	public boolean beyondMinCount()
	{
		Teacher teacher = (Teacher) user;
		return taskService.getTotalCount(teacher) >= teacher.getMinCount();
	}
	
	@Override  
    public void addActionError(String anErrorMessage) {  
        // 这里要先判断一下，是我们要替换的错误，才处理  
        if (anErrorMessage.startsWith("the request was rejected because its size")) {  
            //getText()方法是ActionSupport类中的方法，可以得到国际化文件中的信息  
            super.addActionError(getText("struts.messages.error.file.too.large"));  
            /*或者将该错误定义为fielderror级别：  
             * super.addFieldError("fileTooLargeInfo", "文件的大小超出系统处理的范围");*/  
        } else {// 否则按原来的方法处理  
            super.addActionError(anErrorMessage);  
        }  
    }  
	
	//显示所有可选的课题
	public String showChoiceTasks()
	{
		Student student = (Student) user;
		PageBean<Task> taskPage = this.taskService.getChoicePage(student.getMajor().getId(), tutorQuery, student.getId(), pageNum, pageSize);
		this.request.put("taskPage", taskPage);
		this.request.put("selectedTask", studentTaskService.getStudentTask(student));
		return "choiceTasksPage";
	}
	
	//返回可选择的导师
	public List<Teacher> getChoiceTutors()
	{
		List<Teacher> choiceTutors = this.taskService.getChoiceTutors(((Student) user).getMajor().getId());
		Teacher all = new Teacher();
		all.setId("00");
		all.setName("全部");
		choiceTutors.add(0, all);
		return choiceTutors;
	}
	
	//显示课题详细信息（学生查看版）
	public String showTaskInfo()
	{
		this.model = taskService.getTask(taskId);
		request.put("teacher", model.getTutor());
		request.put("currentPage", pageNum);
		request.put("pageSize", pageSize);
		return "taskInfoPage";
	}
	
	//显示课题详细信息（老师查看版）
	public String showTaskInfo2()
	{
		this.model = taskService.getTask(taskId);
		return "taskInfo2Page";
	}
	
	//返回选择了该课题的人数
	public Long getSelectedCount(int taskId)
	{
		return studentTaskService.getSelectedCount(taskId);
	}
	
	//返回指定课题是否为我的已选课题
	public boolean isMySelectedTask(Integer taskId)
	{
		return this.studentTaskService.isMySelectedTask(user.getId(), taskId);
	}
	
	
	//学生自拟课题
	public String stuProposeTask()
	{
		if(this.studentTaskService.isSelected((Student) user))
			this.model = this.taskService.getTaskByStudent((Student)user);
		
		this.request.put("choiceTutors", teacherService.findChoiceTutors(user.getDepartment().getId()));
		return "stuProposeTaskPage";
	}
	
	//保存或更新学生自拟的课题
	public String saveOrUpdateStuTask() throws Exception
	{
		String dir = sc.getRealPath("/upload/学生拟题审批表");
		this.taskService.saveOrUpdateStuTask(model, (Student)user, file, dir, fileFileName);
		return "choiceTasksAction";
	}
	
	//教师上传学生拟题审批表
	public String uploadStuTaskTable() throws Exception
	{
		String dir = sc.getRealPath("/upload/学生拟题审批表");
		this.taskService.uploadStuTaskTable(taskId, file, dir, fileFileName);
		return "taskInfo2Action";
	}
	
	//去学生自拟课题申请页面
	public String showStuProposeTasks()
	{
		tasks =  taskService.getStuProposeTasks((Teacher)user);
		this.request.put("currentCount", studentTaskService.getCurrentCount((Teacher)user));
		return "stuProposeTasksPage";
	}
	
	//返回是否可以更新学生自拟课题是否通过
	public boolean isUpdateStuProposeTasks()
	{
		String dId = user.getDepartment().getId();
		return scheduleService.beforeSelectBegin(dId) && scheduleService.beyondStuProposeExpiry(dId);
	}
	
	//批量更新学生自拟课题
	public String batchUpdateStuProposeTasks()
	{
		this.taskService.batchUpdateStuProposeTasks(tasks);
		return "stuProposeTaskAction";
	}
	
	public String getMajorQuery() {
		return majorQuery;
	}

	public void setMajorQuery(String majorQuery) {
		this.majorQuery = majorQuery;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Task> getHistoryTasks() {
		return historyTasks;
	}

	public void setHistoryTasks(List<Task> historyTasks) {
		this.historyTasks = historyTasks;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	public String getTutorQuery() {
		return tutorQuery;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTutorQuery(String tutorQuery) {
		this.tutorQuery = tutorQuery;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
