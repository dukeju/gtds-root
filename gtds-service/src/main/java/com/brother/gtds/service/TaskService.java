package com.brother.gtds.service;

import java.io.File;
import java.util.List;

import com.brother.gtds.model.Student;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.page.PageBean;

public interface TaskService extends BaseService<Task> {

	//删除存在超过一定年份的课题
	void deleteBeyondYears(int year);

	//更新或拟定课题
	void saveOrUpdateTask(Task model, File file, String dir, String fileName) throws Exception;

	//查找所有通过审核的历史课题
	List<Task> findHistoryPassTasks(String majorQuery, Integer year);

	//查找出指定教师本届出的课题
	List<Task> getMyCurrentTasks(String id);

	//查找出指定教师所有出的课题
	List<Task> getMyAllTasks(String id);

	//判断是否可以编辑
	boolean isEditableTask(String dId, Integer tId);

	//检验是否有重名课题
	boolean validateName(Integer taskId, String taskName, Teacher teacher);

	//除去指定课题外的最大课题剩余容量
	int getRestCapacity(Integer id, Teacher teacher);

	//获得本届本教师的课题容量总和
	int getTotalCount(Teacher teacher);

	//获得我的可以选择的课题
	PageBean<Task> getChoicePage(String mId, String tutorQuery, String sId, int pageNum, int pageSize);

	//返回可选择的导师
	List<Teacher> getChoiceTutors(String mId);

	Task getTask(Integer taskId);

	//保存或更新学生自拟的课题
	void saveOrUpdateStuTask(Task model, Student user, File file, String dir, String fileName) throws Exception;

	//返回学生自拟课题集合
	List<Task> getStuProposeTasks(Teacher user);

	//返回学生的自拟课题
	Task getTaskByStudent(Student user);

	//批量更新学生自拟课题
	void batchUpdateStuProposeTasks(List<Task> tasks);
	
	//是否是学生自拟课题
	boolean isStudentTask(Integer tId);

	//教师上传学生拟题审批表
	void uploadStuTaskTable(Integer taskId, File file, String dir,
			String fileFileName) throws Exception;
	
}
