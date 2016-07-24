package com.brother.gtds.service;

import java.util.List;

import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;

public interface TaskService extends BaseService<Task> {

	//删除存在超过一定年份的课题
	void deleteBeyondYears(int year);

	//更新或拟定课题
	void saveOrUpdateTask(Task model);

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
	
}
