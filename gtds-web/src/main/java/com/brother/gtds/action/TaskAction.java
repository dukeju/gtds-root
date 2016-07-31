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

import com.brother.gtds.action.aware.TeacherAware;
import com.brother.gtds.model.Major;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;
import com.brother.gtds.service.DepartmentService;
import com.brother.gtds.service.MajorService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.utils.FileUtils;
import com.brother.gtds.utils.ValidationUtils;

@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task> implements TeacherAware, ServletContextAware, RequestAware{

	private static final long serialVersionUID = 6887834321049118028L;
	
	@Resource
	private TaskService taskService;
	@Resource
	private MajorService majorService;
	@Resource
	private DepartmentService departmentService;
	
	private InputStream inputStream;
	
	//接收Teacher
	private Teacher teacher;
	
	private Integer taskId;
	private String taskName;
	
	//面向的专业
	private String majorQuery;
	//查看近几年的课题，最大值为3. 0表示本届
	private Integer year = 3;
	private List<Task> historyTasks;
	private List<Task> myTasks;
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
		request.put("minCount", teacher.getMinCount());
		request.put("maxCount", teacher.getMaxCount());
		
		myTasks = taskService.getMyCurrentTasks(teacher.getId());
		return "myTaskListPage";
	}
	
	//到达我的课题页面（显示全部的）
	public String myAllTasks()
	{
		request.put("minCount", teacher.getMinCount());
		request.put("maxCount", teacher.getMaxCount());
		
		myTasks = taskService.getMyAllTasks(teacher.getId());
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
			model.setTutor(teacher);
			
			if(file != null)
			{
				String dir = sc.getRealPath("/upload/教师拟题审批表");
				String extension = fileFileName.substring(fileFileName.lastIndexOf("."));
				long l = System.nanoTime();
				//保存文件
				FileUtils.saveFile(file, new File(dir, l + extension));
				//删除原来的文件
				FileUtils.deleteFile(new File(dir, model.getPath()));
				//保存新的文件名
				String path = l + extension;
				model.setPath(path);
			}
			
			taskService.saveOrUpdateTask(model);
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
		if(taskService.isEditableTask(teacher.getDepartment().getId(), tId))
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
		if(taskService.validateName(taskId, taskName, teacher))
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		else
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
		return "validateNameAjax";
	}
	
	//除去指定课题外的最大课题剩余容量
	public int getRestCapacity(Integer id)
	{
		return taskService.getRestCapacity(id, teacher);
	}
	
	//是否超过导师的最小指导人数
	public boolean beyondMinCount()
	{
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
		this.teacher = (Teacher) user;
	}

	public List<Task> getMyTasks() {
		return myTasks;
	}

	public void setMyTasks(List<Task> myTasks) {
		this.myTasks = myTasks;
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

}
