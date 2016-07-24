package com.brother.gtds.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brother.gtds.model.Student;
import com.brother.gtds.model.Task;
import com.brother.gtds.model.Teacher;
import com.brother.gtds.service.StudentService;
import com.brother.gtds.service.TaskService;
import com.brother.gtds.service.TeacherService;

public class ServiceTest {

//	private ApplicationContext ac;
//	private StudentService studentService;
//	private TeacherService teacherService;
//	private TaskService taskService;
//	
//	{
//		ac = new ClassPathXmlApplicationContext("beans.xml");
//		studentService = (StudentService) ac.getBean("studentService");
//		teacherService = (TeacherService) ac.getBean("teacherService");
//		taskService = (TaskService) ac.getBean("taskService");
//	}
//	
//	@Test
//	public void testHQL()
//	{
//		String hql = "select s from Student s where s.name = '小豪'";
//		List<Student> list = studentService.findEntityByHQL(hql);
//		System.out.println(list);
//	}
//	
//	@Test
//	public void testFindStudents()
//	{
//		List<Student> list = studentService.findByQuery("", "", "37", "08", "", "");
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testFindTeachers()
//	{
//		List<Teacher> list = teacherService.findByQuery("2006020002", "丁天翔", "08",  "1", "1");
//		System.out.println(list);
//	}
//	
//	@Test
//	public void testUpdateType()
//	{
//		teacherService.updateType("2006020002", 1);
//	}
//	
//	@Test
//	public void testFindHistoryPassTask()
//	{
//		List<Task> list = taskService.findHistoryPassTasks(null, 3);
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testGetMyCurrentTasks()
//	{
//		List<Task> list = teacherService.getMyCurrentTasks("2006020002");
//		System.out.println(list);
//	}
//	
//	@Test
//	public void testGetMyAllTasks()
//	{
//		List<Task> list = teacherService.getMyAllTasks("2006020002");
//		System.out.println(list);
//	}
//	
//	@Test
//	public void testSaveOrUpdateTask()
//	{
////		Task t = taskService.getEntity(10);
////		t.setId(11);
////		t.setName("系统开发2");
////		t.setPass(false);
////		t.setPublishDate(new Date());
////		t.setMajor(new Major("37"));
////		Teacher tea = new Teacher("2006020002");
////		tea.setDepartment(new Department("08"));
////		t.setTutor(tea);
////		
////		taskService.saveOrUpdateTask(t);
//	}
//	
//	@Test
//	public void testDeleteTask()
//	{
//		taskService.deleteTask(4);
//	}
//	
//	
//	
}
