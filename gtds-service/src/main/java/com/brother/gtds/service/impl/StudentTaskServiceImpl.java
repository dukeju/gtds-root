package com.brother.gtds.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;
import com.brother.gtds.service.StudentTaskService;

@Service("studentTaskService")
public class StudentTaskServiceImpl extends BaseServiceImpl<StudentTask> implements
		StudentTaskService {

	@Resource(name="studentTaskDao")
	@Override
	public void setDao(BaseDao<StudentTask> dao) {
		super.setDao(dao);
	}

	//学生选择课题
	@Override
	public void selectTask(Integer taskId, Student student) {
		Task t = new Task();
		t.setId(taskId);
		StudentTask st = new StudentTask();
		st.setStudent(student);
		st.setTask(t);
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
	}
	
}
