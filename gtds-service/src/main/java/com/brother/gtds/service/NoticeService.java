package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Admin;
import com.brother.gtds.model.Notice;
import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.model.User;

public interface NoticeService extends BaseService<Notice> {

	void saveOrUpdateNotice(Notice notice);

	//发送指导人数未达到要求通知
	void dissatisfyNumber(Admin admin, String tId);
	
	//发送所有指导人数未达到要求通知
	void allDissatisfyNumber(Admin admin, String tIds);

	//返回有关自己的通知的集合(可以指定是否已读)
	List<Notice> getMyNotices(User user, boolean read);

	//确认通知
	void confirmNotice(Integer nId);
	
	Notice getNotice(Integer id);

	//通知导师学生自拟课题
	void stuProposeTask(Student student, Teacher teacher, Task task);

	//返回是否有未读消息
	boolean hasUnreadNotice(User user);

	//通知学生自拟课题结果
	void stuTaskResult(Task t);

	//通知学生选题结果
	void choiceTaskResult(StudentTask st);
}
