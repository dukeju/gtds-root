<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'success.jsp' starting page</title>

  </head>
  
  <body>
    success!
    <br/>
    ${sessionScope.user.name }
    <br>
    <a href="StudentAction_showStudents">学生信息</a>
    <a href="TeacherAction_showTeachers">教师信息</a>
    <br>
    <a href="TaskAction_showHistoryTasks">查看历史课程</a>
    <a href="TaskAction_myCurrentTasks">我的课题</a>
  </body>
</html>
