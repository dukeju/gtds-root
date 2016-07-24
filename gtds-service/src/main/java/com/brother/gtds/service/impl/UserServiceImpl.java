package com.brother.gtds.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brother.gtds.model.User;
import com.brother.gtds.service.AdminService;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.service.TeacherService;
import com.brother.gtds.service.UserService;
import com.brother.gtds.utils.ValidationUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private AdminService adminService;
	
	//验证用户信息
	@SuppressWarnings("rawtypes")
	@Override
	public User validateUserInfo(String id, String password, Integer status) {
		List list = null;
		String hql = "";
		if(status == 0)
		{
			hql = "from Student s where s.id = ? and s.password = ?";
			list = studentService.findEntityByHQL(hql, id, password);
		}
		if(status == 1)
		{
			hql = "from Teacher t where t.id = ? and t.password = ?";
			list = teacherService.findEntityByHQL(hql, id, password);
		}
		if(status == 2)
		{
			hql = "from Admin a where a.id = ? and a.password = ?";
			list = adminService.findEntityByHQL(hql, id, password);
		}
		return (User) (ValidationUtils.validateColl(list) ? list.get(0) : null);
	}

}
