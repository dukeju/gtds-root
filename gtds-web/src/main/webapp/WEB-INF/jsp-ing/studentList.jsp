<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>学生列表</title>

  </head>
  
  <body>
  	<s:form action="StudentAction_showStudents" method="post">
  		学号：<s:textfield name="idQuery"></s:textfield><br>
  		姓名：<s:textfield name="nameQuery"></s:textfield><br>
  		课题：<s:textfield name="taskQuery"></s:textfield><br>
  		导师：<s:textfield name="tutorQuery"></s:textfield><br>
  		专业：<s:select list="%{majors}" listKey="id" listValue="majorName" name="majorQuery"></s:select><br>
  		学院：<s:select list="%{departments}" listKey="id" listValue="name" name="departmentQuery"></s:select><br>
  		<s:submit value="确定"></s:submit>
  	</s:form>
  	<table width="100%" border="1" cellspacing="0">
  		<tr>
  			<td>学号</td>
  			<td>姓名</td>
  			<td>专业</td>
  			<td>班级</td>
  			<td>学院</td>
  			<td>指导老师</td>
  			<td>课题</td>
  			<td>论文题目</td>
  			<td>评阅老师</td>
  			<td>邮箱</td>
  			<td>联系方式</td>
  		</tr>
  		<s:iterator value="%{students}">
  			<tr>
  				<td><s:property value="id" /></td>
  				<td><s:property value="name" /></td>
  				<td><s:property value="major.majorName" /></td>
  				<td><s:property value="cla" /></td>
  				<td><s:property value="department.name" /></td>
  				<td><s:property value="tutor.name" /></td>
  				<td><s:property value="task.name" /></td>
  				<td><s:property value="thesis.title" /></td>
  				<td><s:property value="inspector.name" /></td>
  				<td><s:property value="email" /></td>
  				<td><s:property value="phone" /></td>
  			</tr>
  		</s:iterator>
  	</table>
  </body>
</html>
