package com.brother.gtds.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brother.gtds.model.Student;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.service.TeacherService;

public class ServiceTest {

	private ApplicationContext ac;
	private StudentService studentService;
	private TeacherService teacherService;
	
	{
		ac = new ClassPathXmlApplicationContext("beans.xml");
		studentService = (StudentService) ac.getBean("studentService");
		teacherService = (TeacherService) ac.getBean("teacherService");
	}
	
	@Test
	public void testHQL()
	{
		String hql = "select s from Student s where s.name = '小豪'";
		List<Student> list = studentService.findEntityByHQL(hql);
		System.out.println(list);
	}
	
	@Test
	public void testFindStudents()
	{
		List<Student> list = studentService.findByQuery("", "", "信息管理与信息系统", "", "", "");
		System.out.println(list.size());
	}
	
	@Test
	public void testFindTeachers()
	{
		List<Teacher> list = teacherService.findByQuery("2006020002", "丁天翔", "1", "0");
		System.out.println(list);
	}
	
	@Test
	public void testUpdateType()
	{
		teacherService.updateType("2006020002", 1);
	}
	
}
