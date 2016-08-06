<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'success.jsp' starting page</title>

  </head>
  
  <body>
  	<s:a action="LoginAction_toLogin">登陆</s:a>
  	<br>
    success!
    <br/>
    ${sessionScope.user.name }
    <br>
    <s:set value="getMyNotices()" var="notices"></s:set>
    <s:if test="#notices != null">
    	<table width="100%" border="1" cellspacing="0">
    		<tr>
    			<td></td>
    			<td>发送者</td>
    			<td>标题</td>
    			<td>日期</td>
    			<td>查看</td>
    		</tr>
    		<s:iterator value="#notices" status="st">
    			<tr>
    				<td><s:property value="#st.index + 1" /></td>
    				<td>
						<s:property value="%{adminSender != null? adminSender.name : teaSender.name}" />
					</td>
    				<td><s:property value="title" /></td>
    				<td><s:date format="yyyy-MM-dd HH:mm:ss" name="date"></s:date></td>
    				<td><s:a action="NoticeAction_noticeInfo?nId=%{id}">查看</s:a></td>
    			</tr>
    		</s:iterator>
    	</table>
    </s:if>
    
    <br>
    <a href="StudentAction_showStudents">学生信息</a>
    <a href="TeacherAction_showTeachers">教师信息</a>
    <br>
    <a href="TaskAction_showHistoryTasks">查看历史课程</a>
    <a href="TaskAction_myCurrentTasks">我的课题</a>
    <br>
    <a href="TeacherAction_showTeacherTaskSituation">教师出题情况</a>
    <br>
    <a href="TaskAction_showChoiceTasks">选题列表</a>
  </body>
</html>
