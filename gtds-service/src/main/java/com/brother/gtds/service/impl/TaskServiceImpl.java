package com.brother.gtds.service.impl;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Department;
import com.brother.gtds.model.Major;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.DepartmentService;
import com.brother.gtds.service.MajorService;
import com.brother.gtds.service.NoticeService;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.service.StudentTaskService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.service.page.PageBean;
import com.brother.gtds.utils.FileUtils;
import com.brother.gtds.utils.ValidationUtils;

@Service("taskService")
public class TaskServiceImpl extends BaseServiceImpl<Task> implements
		TaskService {

	@Resource
	private SessionFactory factory;
	
	@Resource
	private DepartmentService departmentService;
	@Resource
	private StudentTaskService studentTaskService;
	@Resource
	private StudentService studentService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private MajorService majorService;
	
	@Override
	@Resource(name="taskDao")
	public void setDao(BaseDao<Task> dao) {
		super.setDao(dao);
	}
	
	//查找所有通过审核的历史课题
	public List<Task> findHistoryPassTasks(String majorQuery, Integer year)
	{
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR) - year;
		Date date = Date.valueOf(year + "-1-1");
		String hql = "from Task t where t.publishDate >= ? and t.pass = ?";
		
		if(ValidationUtils.validateString(majorQuery))
		{
			//如果条件不是全部专业
			if(!majorQuery.equals("00"))
				hql += " and t.major.id = " + majorQuery;
		}
		//控制其排序
		hql += " order by t.major asc, t.publishDate desc";
		List<Task> tasks = this.findEntityByHQL(hql, date, true);
		
		//解决懒加载问题
		for(Task task : tasks)
		{
			task.getMajor().getMajorName();
			task.getTutor().getName();
		}
		return tasks;
	}
	
	//查找出指定教师本届出的课题
	public List<Task> getMyCurrentTasks(String id)
	{
		Teacher t = teacherService.getEntity(id);
		java.util.Date date = t.getDepartment().getProposeBegin();
		String hql = "from Task t where t.tutor.id = ? and t.publishDate >= ? "
				+ "order by t.publishDate asc";
		List<Task> tasks = this.findEntityByHQL(hql, id, date);
		
		//解决懒加载问题
		for(Task task : tasks)
		{
			task.getMajor().getMajorName();
			if(task.getStudent() != null)
				task.getStudent().getName();
		}
		return tasks;
	}

	//查找出指定教师所有出的课题
	@Override
	public List<Task> getMyAllTasks(String id) {
		String hql = "from Task t where t.tutor.id = ? order by t.publishDate desc";
		List<Task> tasks = this.findEntityByHQL(hql, id);
		
		//解决懒加载问题
		for(Task task : tasks)
		{
			task.getMajor().getMajorName();
			if(task.getStudent() != null)
				task.getStudent().getName();
		}
		return tasks;
	}	

	//删除存在超过一定年份的课题
	@Override
	public void deleteBeyondYears(int years) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) - years - 1;
		Date date = Date.valueOf(year + "-1-1");
		String hql = "delete from Task t where t.publishDate < ?";
		this.BatchEntityByHQL(hql, date);
	}

	//更新或拟定课题
	@Override
	public void saveOrUpdateTask(Task model, File file, String dir, String fileName) throws Exception {
//		//判断是否超过截止出题日期
//		if(departmentService.beyondExpiryDate(model.getTutor().getDepartment().getId()))
//			System.out.println("已经超过了截止出题日期，无法拟定！该课题无效!");
//		else
//		{
//			List<Task> tasks = this.getMyCurrentTasks(model.getTutor().getId());
//			for(Task task : tasks)
//			{
//				//如果与本届课题重名，且重名课题已经通过，则不保存
//				if(task.getName().equals(model.getName()) && task.isPass())
//				{
//					//跳过同一课题的检验
//					if(model.getId() != null && model.getId() == task.getId())
//						continue;
//					System.out.println("与本届的课题重名，拟定无效！");
//					return;
//				}
//			}
//			
//			/*
//			清空session，否则会报异常org.springframework.orm.hibernate.HibernateSystemException: 
//			a different object with the same identifier value was already associated with the session
//			(与上面调用的getMyCurrentTasks()方法冲突)
//			 */
//			factory.getCurrentSession().clear();
			if(file != null)
			{
				String extension = fileName.substring(fileName.lastIndexOf("."));
				long l = System.nanoTime();
				//保存文件
				FileUtils.saveFile(file, new File(dir, l + extension));
				//删除原来的文件
				FileUtils.deleteFile(new File(dir, model.getPath()));
				//保存新的文件名
				String path = l + extension;
				model.setPath(path);
			}
			model.setPublishDate(new java.util.Date());
			this.saveOrUpdateEntity(model);
//		}
	}

	//判断是否可以编辑
	@Override
	public boolean isEditableTask(String dId, Integer tId) {
		//是否可以新建课题的情况
		if(tId == null)
		{
			if(!departmentService.beforeProposeBegin(dId) && !departmentService.beyondProposeExpiry(dId))
				return true;
			return false;
		}
		//是否可以修改课题的情况
		else
		{
			Task task = this.getEntity(tId);
			Department d = departmentService.getEntity(dId);
			//本届发布的课题
			if(!departmentService.beyondProposeExpiry(dId) && task.getPublishDate().after(d.getProposeBegin()))
				return true;
			return false;
		}
	}

	//检验是否有重名课题
	@Override
	public boolean validateName(Integer taskId, String taskName, Teacher teacher) {
		List<Task> tasks = this.getMyCurrentTasks(teacher.getId());
		for(Task task : tasks)
		{
			//如果与本届课题重名，且重名课题已经通过，则不保存
			if(task.getName().equals(taskName) && task.isPass())
			{
				//跳过同一课题的检验
				if(taskId != null && taskId == task.getId())
					continue;
				return false;
			}
		}
		return true;
	}

	//除去指定课题外的最大课题剩余容量
	@Override
	public int getRestCapacity(Integer id, Teacher teacher) {
		int capacity = teacher.getMaxCount();
		List<Task> tasks = this.getMyCurrentTasks(teacher.getId());
		for(Task task : tasks)
		{
			//跳过同一课题的检验
			if(id != null && id == task.getId())
				continue;
			capacity = capacity - task.getCapacity();	
		}
		return capacity;
	}

	//获得本届本教师的课题容量总和
	@Override
	public int getTotalCount(Teacher teacher) {
		List<Task> tasks = this.getMyCurrentTasks(teacher.getId());
		int count = 0;
		for(Task t : tasks)
		{
			if(t.isPass())
				count += t.getCapacity() == null? 0 : t.getCapacity();
		}
		return count;
	}

	//获得我的可以选择的课题
	@Override
	public PageBean<Task> getChoicePage(String mId, String tutorQuery, String sId, int pageNum, int pageSize) {
		PageBean<Task> page = null;
		String hql = "from Task t where t.publishDate >= ? and t.major.id = ? "
				+ "and t.pass = ? and t.student.id is null";
		
		//不包括自己已选的课题
		if(ValidationUtils.validateString(sId))
		{
			String hql2 = "from StudentTask st where st.student.id = ?";
			StudentTask st = (StudentTask) this.uniqueResult(hql2, sId);
			if(st != null)
				hql += " and t.id != " + st.getTask().getId();
		}
		
		if(ValidationUtils.validateString(tutorQuery) && !tutorQuery.equals("00"))
		{
			hql += " and t.tutor.id = " + tutorQuery;
		}
		else
		{
			hql += " order by t.tutor.name asc";
		}
		Major m = majorService.getEntity(mId);
		page = this.getPageBean(pageNum, pageSize, hql, m.getDepartment().getProposeBegin(), mId, true);
		
		
		for(Task t : page.getList())
		{
			t.getMajor().getMajorName();
			t.getTutor().getName();
		}
		return page;
	}

	//返回可选择的导师
	@Override
	public List<Teacher> getChoiceTutors(String mId) {
		//获得全部课题
		List<Task> tasks = this.getChoicePage(mId, "00", null, 1, Integer.MAX_VALUE).getList();
		List<Teacher> tutors = new ArrayList<Teacher>();
		for(Task t : tasks)
		{
			Teacher tutor = t.getTutor();
			if(!tutors.contains(tutor))
				tutors.add(tutor);
		}
		return tutors;
	}

	@Override
	public Task getTask(Integer taskId) {
		Task t = this.getEntity(taskId);
		t.getTutor().getName();
		t.getTutor().getDepartment().getName();
		t.getMajor().getMajorName();
		return t;
	}

	//保存或更新学生自拟的课题
	@Override
	public void saveOrUpdateStuTask(Task model, Student user, File file, String dir, String fileName) throws Exception {
		model.setStudent(user);
		model.setMajor(this.studentService.getEntity(user.getId()).getMajor());
		if(model.getId() == null)
		{
			model.setPass(false);
			model.setCapacity(1);
			//先保存Task，才能再保存StudentTask
			this.saveOrUpdateTask(model, file, dir, fileName);
			this.studentTaskService.selectTask(model.getId(), user);
		}
		else
			this.saveOrUpdateTask(model, file, dir, fileName);
		
		//通知导师
		noticeService.stuProposeTask(user, model.getTutor(), model);
	}

	//返回学生自拟课题集合
	@Override
	public List<Task> getStuProposeTasks(Teacher user) {
		String hql = "from Task t where t.tutor.id = ? and t.publishDate >= ? "
				+ "and t.student.id is not null";
		user = teacherService.getEntity(user.getId());
		List<Task> tasks = this.findEntityByHQL(hql, user.getId(), user.getDepartment().getProposeBegin());
		for(Task t : tasks)
		{
			t.getStudent().getName();
			t.getMajor().getMajorName();
		}
		return tasks;
	}

	//返回学生的自拟课题
	@Override
	public Task getTaskByStudent(Student user) {
		String hql = "from Task t where t.student.id = ?";
		Task t = (Task) this.uniqueResult(hql, user.getId());
		
		if(t != null)
			t.getTutor().getName();
		
		return t;
	}

	//批量更新学生自拟课题结果
	@Override
	public void batchUpdateStuTasks(List<Task> tasks) {
		if(ValidationUtils.validateColl(tasks))
		{
			String hql1 = "update Task t set t.pass = ? where t.id = ?";
			String hql2 = "update StudentTask st set st.pass = ? where st.task.id = ? "
					+ "and st.student.id = ?";
			for(int i=0; i<tasks.size(); i++)
			{
				Task t = tasks.get(i);
				this.BatchEntityByHQL(hql1, t.isPass(), t.getId());
				this.studentTaskService.BatchEntityByHQL(hql2, t.isPass(), t.getId(), 
						this.getEntity(t.getId()).getStudent().getId());
				
				//通知学生自拟课题结果
				this.noticeService.stuTaskResult(t);
			}
		}
	}

	//是否是学生自拟课题
	@Override
	public boolean isStudentTask(Integer tId) {
		Task t = this.getEntity(tId);
		return t.getStudent() != null;
	}

}
