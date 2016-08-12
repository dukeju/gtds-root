package com.brother.gtds.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;
import com.brother.gtds.service.NoticeService;
import com.brother.gtds.service.StudentTaskService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.utils.ValidationUtils;

@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements
		NoticeService {

	@Resource
	private TeacherService teacherService;
	@Resource
	private TaskService taskService;
	@Resource
	private StudentTaskService studentTaskService;
	
	@Resource(name="noticeDao")
	@Override
	public void setDao(BaseDao<Notice> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateNotice(Notice notice) {
		notice.setDate(new Date());
		this.saveOrUpdateEntity(notice);
	}

	//发送指导人数未达到要求通知
	@Override
	public void dissatisfyNumber(Admin admin, String tId) {
		Notice notice = new Notice();
		notice.setAdminSender(admin);
		Teacher t = teacherService.getEntity(tId);
		notice.setTeaReceiver(t);
		notice.setTitle("指导人数未达到要求通知");
		notice.setMessage("你的最小指导人数为"+ t.getMinCount() +"，最大指导人数为"+ t.getMaxCount() +
				"，目前指导人数为"+ taskService.getTotalCount(t) +"，请尽快满足要求！");
		this.saveOrUpdateNotice(notice);
	}

	//发送所有指导人数未达到要求通知
	@Override
	public void allDissatisfyNumber(Admin admin, String tIds) {
		String[] arr = tIds.substring(0, tIds.length() - 1).split(",");
		for(int i = 0; i < arr.length; i++)
		{
			Notice notice = new Notice();
			notice.setAdminSender(admin);
			notice.setTitle("指导人数未达到要求通知");
			Teacher t = teacherService.getEntity(arr[i]);
			notice.setTeaReceiver(t);
			notice.setMessage("你的最小指导人数为"+ t.getMinCount() +"，最大指导人数为"+ t.getMaxCount() +
					"，目前指导人数为"+ taskService.getTotalCount(t) +"，请尽快满足要求！");
			this.saveOrUpdateNotice(notice);
		}
	}

	//返回有关自己的通知的集合（可以指定是否已读）
	@Override
	public List<Notice> getMyNotices(User user, boolean read) {
		if(user instanceof Admin)
			return null;
		
		String hql = "";
		if(user instanceof Teacher)
			hql = "from Notice n where n.teaReceiver.id = ?";
		else if(user instanceof Student)
			hql = "from Notice n where n.stuReceiver.id = ?";
		hql += " and n.receive = ? order by n.date desc";
		List<Notice> list = this.findEntityByHQL(hql, user.getId(), read);
		for(Notice n : list)
		{
			this.solveLazyLoad(n);
		}
		return list;
	}
	
	//解决懒加载
	private void solveLazyLoad(Notice n)
	{
		if(n.getAdminSender() != null)
			n.getAdminSender().getName();
		if(n.getStuReceiver() != null)
			n.getStuReceiver().getName();
		if(n.getTeaReceiver() != null)
			n.getTeaReceiver().getName();
		if(n.getTeaSender() != null)
			n.getTeaSender().getName();
		if(n.getDapartment() != null)
			n.getDapartment().getName();
		if(n.getStuSender() != null)
			n.getStuSender().getName();
	}

	//确认通知
	@Override
	public void confirmNotice(Integer nId) {
		Notice notice = this.getEntity(nId);
		notice.setReceive(true);
	}

	@Override
	public Notice getNotice(Integer id) {
		Notice n = this.getEntity(id);
		
		this.solveLazyLoad(n);
		return n;
	}

	//通知导师学生自拟课题
	@Override
	public void stuProposeTask(Student student, Teacher teacher, Task task) {
		Notice n = new Notice();
		n.setStuSender(student);
		n.setTeaReceiver(teacher);
		n.setTitle(student.getName() + "同学向您申请自拟课题");
		n.setMessage(student.getName() + "同学向您申请自拟课题" + task.getName() 
				+ "，请你尽快给予答复是否通过。");
		this.saveOrUpdateNotice(n);
	}

	//返回是否有未读消息
	@Override
	public boolean hasUnreadNotice(User user) {
		if(user instanceof Admin)
			return false;
		String hql = "from Notice n where n.teaReceiver.id = ? or n.stuReceiver.id = ?";
		List<Notice> list = this.findEntityByHQL(hql, user.getId(), user.getId());
		return ValidationUtils.validateColl(list);
	}

	//通知学生自拟课题结果
	@Override
	public void stuTaskResult(Task t) {
		t = taskService.getEntity(t.getId());
		Notice n = new Notice();
		n.setTeaSender(t.getTutor());
		n.setStuReceiver(t.getStudent());
		n.setTitle("您的自拟课题申请" + (t.isPass()? "成功" : "失败"));
		n.setMessage("经过导师" + t.getTutor().getName() + "的审核， 您的自拟课题申请" 
					+ (t.isPass()? "成功，请按照课题自主完成毕业论文设计。" : "失败，请好自为之。"));
		this.saveOrUpdateNotice(n);
	}

	//通知学生选题结果
	@Override
	public void choiceTaskResult(StudentTask st) {
		st = this.studentTaskService.getEntity(st.getId());
		Notice n = new Notice();
		n.setTeaSender(st.getTutor());
		n.setStuReceiver(st.getStudent());
		n.setTitle("您的选择课题申请" + (st.isPass()? "通过了" : "未能通过"));
		n.setMessage("经过导师" + st.getTutor().getName() + "的审核， 您的选择课题申请" 
				+ (st.isPass()? "通过了，请按照课题自主完成毕业论文设计。" : "未能通过，请好自为之。"));
		this.saveOrUpdateNotice(n);
	}
	
}
