package com.brother.gtds.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.NoticeService;
import com.brother.gtds.service.ScheduleService;
import com.brother.gtds.service.StudentTaskService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.utils.ValidationUtils;

@Service("studentTaskService")
public class StudentTaskServiceImpl extends BaseServiceImpl<StudentTask> implements
		StudentTaskService {

	@Resource
	private TaskService taskService;
	@Resource
	private ScheduleService scheduleService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private NoticeService noticeService;
	
	@Resource(name="studentTaskDao")
	@Override
	public void setDao(BaseDao<StudentTask> dao) {
		super.setDao(dao);
	}

	//学生选择课题
	@Override
	public void selectTask(Integer taskId, Student student) {
		Task t = taskService.getEntity(taskId);
		StudentTask st = new StudentTask();
		st.setStudent(student);
		st.setTask(t);
		st.setTutor(t.getTutor());
		st.setDate(new Date());
		this.saveOrUpdateEntity(st);
	}

	//返回是否选过课题
	@Override
	public boolean isSelected(Student user) {
		String hql = "select count(st) from StudentTask st where st.student.id = ?";
		Long count = (Long) this.uniqueResult(hql, user.getId());
		if(count > 0)
			return true;
		return false;
	}

	//返回选择了该课题的人数
	@Override
	public Long getSelectedCount(int taskId) {
		String hql = "select count(st) from StudentTask st where st.task.id = ?";
		Long count = (Long) this.uniqueResult(hql, taskId);
		return count;
	}

	//返回该学生已选的课题
	@Override
	public Task getSelectedTask(String sId) {
		String hql = "from StudentTask st where st.student.id = ?";
		StudentTask st = (StudentTask) this.uniqueResult(hql, sId);
		if(st == null)
			return null;
		else
		{
			st.getTask().getTutor().getName();
			return st.getTask();
		}
	}

	//返回指定课题是否为我的已选课题
	@Override
	public boolean isMySelectedTask(String sId, Integer taskId) {
		String hql = "from StudentTask st where st.student.id = ?";
		StudentTask st = (StudentTask) this.uniqueResult(hql, sId);
		if(st != null && st.getTask().getId() == taskId)
			return true;
		return false;
	}

	//退选课题
	@Override
	public void unselectTask(Integer taskId, String sId) {
		String hql = "delete from StudentTask st where st.task.id = ?"
				+ " and st.student.id = ?";
		this.BatchEntityByHQL(hql, taskId, sId);
		
		//如果为学生自拟课题
		Task t = taskService.getEntity(taskId);
		if(t.getStudent() != null)
			taskService.deleteEntity(t);
	}

	//该选题是否已经被导师通过
	@Override
	public boolean isPassed(Integer taskId, Student user) {
		String hql = "from StudentTask st where st.task.id = ? and "
				+ "st.student.id = ?";
		StudentTask st = (StudentTask) this.uniqueResult(hql, taskId, user.getId());
		return st.isPass();
	}

	//返回该导师的学生选题集合
	@Override
	public List<StudentTask> getMyStudentTasks(Teacher user) {
		String hql = "from StudentTask st where st.tutor.id = ? and st.date >= ? "
				+ "order by st.pass asc, st.task.id asc, st.date asc";
		user = teacherService.getEntity(user.getId());
		List<StudentTask> list = this.findEntityByHQL(hql, user.getId(), 
				scheduleService.getSchedule(user.getDepartment().getId()).getStuProposeBegin());
		for(StudentTask st : list)
		{
			st.getStudent().getName();
			st.getTask().getName();
		}
		return list;
	}

	//批量更新学生选课情况
	@Override
	public void batchUpdateStuTasks(List<StudentTask> studentTasks) {
		if(ValidationUtils.validateColl(studentTasks))
		{
			String hql = "update StudentTask st set st.pass = ? where st.id = ?";
			for(StudentTask st : studentTasks)
			{
				this.BatchEntityByHQL(hql, st.isPass(), st.getId());
				
				//通知学生选题结果
				this.noticeService.choiceTaskResult(st);
			}
		}
	}

	//返回指定学生选题的StudentTask
	@Override
	public StudentTask getStudentTask(Student student) {
		String hql = "from StudentTask st where st.student.id = ?";
		StudentTask st = (StudentTask) this.uniqueResult(hql, student.getId());
		if(st != null)
		{
			st.getTask().getName();
			st.getTutor().getName();
			if(st.getTask().getStudent() != null)
				st.getTask().getStudent().getId();
		}
		return st;
	}

	//返回导师的目前指导人数
	@Override
	public Long getCurrentCount(Teacher user) {
		String hql = "select count(st) from StudentTask st where st.tutor.id = ? "
				+ "and st.date >= ? and st.pass = ?";
		Teacher t = teacherService.getEntity(user.getId());
		Long count = (Long) this.uniqueResult(hql, user.getId(), 
				scheduleService.getSchedule(t.getDepartment().getId()).getStuProposeBegin(), true);
		return count;
	}
	
}
