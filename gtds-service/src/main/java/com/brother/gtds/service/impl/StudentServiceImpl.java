package com.brother.gtds.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.dao.BaseDao;
import com.brother.gtds.model.Student;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.utils.ValidationUtils;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements
		StudentService {

	@Resource(name="studentDao")
	public void setDao(BaseDao<Student> dao)
	{
		super.setDao(dao);
	}

	//根据查询条件找出学生
	@Override
	public List<Student> findByQuery(String idQuery, String nameQuery,
			String majorQuery, String departmentQuery, String tutorQuery,
			String taskQuery) {
		String hql = "from Student s where ";
		if(ValidationUtils.validateString(idQuery))
		{
			hql += "s.id = '" + idQuery + "' and ";
		}
		if(ValidationUtils.validateString(nameQuery))
		{
			hql += "s.name = '" + nameQuery + "' and ";
		}
		if(ValidationUtils.validateString(majorQuery))
		{
			hql += "s.major.majorName = '" + majorQuery + "' and ";
		}
		if(ValidationUtils.validateString(departmentQuery))
		{
			hql += "s.department.name = '" + departmentQuery + "' and ";
		}
		if(ValidationUtils.validateString(tutorQuery))
		{
			hql += "s.tutor.name = '" + tutorQuery + "' and ";
		}
		if(ValidationUtils.validateString(taskQuery))
		{
			hql += "s.task.name = '" + taskQuery + "' and ";
		}
		
		//如果查询条件都为空
		if(hql.equals("from Student s where "))
			hql = "from Student";
		else
			hql = hql.substring(0, hql.length() - 5);
		List<Student> list = this.findEntityByHQL(hql);
		//解决懒加载问题
		for(Student s : list)
		{
			s.getDepartment().getName();
			s.getMajor().getMajorName();
//			s.getTutor().getName();
//			s.getTask().getName();
//			s.getThesis().getTitle();
//			s.getInspector().getName();
//			s.getGroup().getPlace();
		}
		return list;
	}

}
