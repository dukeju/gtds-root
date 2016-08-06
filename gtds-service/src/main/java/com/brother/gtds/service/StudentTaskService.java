package com.brother.gtds.service;

import com.brother.gtds.model.Student;
import com.brother.gtds.model.StudentTask;
import com.brother.gtds.model.Task;

public interface StudentTaskService extends BaseService<StudentTask> {

	//学生选择课题
	void selectTask(Integer taskId, Student student);

	//返回是否选过课题
	boolean isSelected(Student user);

	//返回选择了该课题的人数
	Long getSelectedCount(int taskId);

	//返回该学生已选的课题
	Task getSelectedTask(String sId);

	//返回指定课题是否为我的已选课题
	boolean isMySelectedTask(String sId, Integer taskId);

	//退选课题
	void unselectTask(Integer taskId, String sId);

}
