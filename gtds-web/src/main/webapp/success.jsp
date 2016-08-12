<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>首页</title>

  </head>
  
  <body>
  	<s:a action="LoginAction_toLogin">登陆</s:a>
  	<br>
    success!
    <br/>
    ${sessionScope.user.name }
    <br>
    <s:a action="NoticeAction_getMyNotices">我的消息</s:a>
    <s:set value="hasUnreadNotice()" var="hasUnreadNotice"></s:set>
    <s:if test="#hasUnreadNotice">
    	<font color="red">（你有未读的消息）</font>
    </s:if>
    
    <br>
    <a href="StudentAction_showStudents">学生信息</a>
    <a href="TeacherAction_showTeachers">教师信息</a>
    <br>
    <a href="TaskAction_showHistoryTasks">查看历史课程</a>
    <a href="TaskAction_myCurrentTasks">我的课题</a>
    <a href="TaskAction_showStuProposeTasks">自拟课题申请</a>
    <a href="StudentAction_showMyStudentTasks">学生选题申请</a>
    <br>
    <a href="TeacherAction_showTeacherTaskSituation">教师出题情况</a>
    <br>
    <a href="TaskAction_showChoiceTasks">选题列表</a>
  </body>
</html>
